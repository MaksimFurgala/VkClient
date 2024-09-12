package com.example.vkclient.data.repository

import android.content.Context
import android.util.Log
import com.example.vkclient.data.mapper.FeedPostMapper
import com.example.vkclient.data.network.VkApiFactory
import com.example.vkclient.domain.entity.AuthState
import com.example.vkclient.domain.entity.Comment
import com.example.vkclient.domain.entity.FeedPost
import com.example.vkclient.domain.entity.StatisticPostItem
import com.example.vkclient.domain.entity.StatisticType
import com.example.vkclient.domain.repository.FeedPostRepository
import com.example.vkclient.extensions.mergeWith
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class FeedPostRepositoryImpl @Inject constructor(
    val context: Context,
    private val storage: VKPreferencesKeyValueStorage,
    private val feedPostMapper: FeedPostMapper,
) :
    FeedPostRepository {

    /**
     * Access-токен для доступа к сервисам VK.
     */
    private val token
        get() = VKAccessToken.restore(storage)


    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    private val nextDataNeededEvents = MutableSharedFlow<Unit>(replay = 1)
    private val refreshedListFlow = MutableSharedFlow<List<FeedPost>>()
    private val loadedListFlow = flow {
        nextDataNeededEvents.emit(Unit)
        nextDataNeededEvents.collect {
            val startFrom = nextFromFeedPost

            if (startFrom == null && feedPosts.isNotEmpty()) {
                emit(feedPosts)
                return@collect
            }

            val response = if (startFrom == null) {
                vkApiService.loadFeedPosts(getAccessToken())
            } else {
                vkApiService.loadFeedPosts(getAccessToken(), startFrom)
            }

            nextFromFeedPost = response.feedPostContent.nextFrom
            val posts = feedPostMapper.mapResponseToFeedPosts(response)
            _feedPosts.addAll(posts)
            emit(feedPosts)
        }
    }.retry {
        delay(RETRY_TIMEOUT_MILLIS)
        true
    }

    /**
     * Vk api service
     */
    private val vkApiService = VkApiFactory.vkApiService

    /**
     * Тек. список новостных постов.
     */
    private val _feedPosts = mutableListOf<FeedPost>()
    private val feedPosts: List<FeedPost>
        get() = _feedPosts.toList()

    private var nextFromFeedPost: String? = null

    private val checkAuthStateEvents = MutableSharedFlow<Unit>(replay = 1)

    private val authStateFlow = flow {
        checkAuthStateEvents.emit(Unit)
        checkAuthStateEvents.collect {
            val currentToken = token
            val loggedIn = currentToken != null && currentToken.isValid
            val authState = if (loggedIn) AuthState.Authorized else AuthState.NotAuthorized
            emit(authState)

            Log.d("MainViewModel", "Token: ${currentToken?.accessToken}")
        }
    }.stateIn(
        scope = coroutineScope,
        started = SharingStarted.Lazily,
        initialValue = AuthState.Initial
    )

    /**
     * Загрузка новостных постов.
     *
     * @return - список новостных постов
     */
    private val recommendations: StateFlow<List<FeedPost>> = loadedListFlow
        .mergeWith(refreshedListFlow)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.Lazily,
            initialValue = feedPosts
        )

    override fun getAuthState(): StateFlow<AuthState> = authStateFlow

    override fun getRecommendations(): StateFlow<List<FeedPost>> = recommendations

    override suspend fun loadNextData() {
        nextDataNeededEvents.emit(Unit)
    }

    override suspend fun checkAuthState() {
        checkAuthStateEvents.emit(Unit)
    }

    /**
     * Получение access-токена.
     *
     * @return access-токен
     */
    private fun getAccessToken(): String {
        return token?.accessToken ?: throw IllegalStateException("Access token is null")
    }

    override suspend fun ignoreFeedPost(feedPost: FeedPost) {
        vkApiService.ignoreFeedPost(
            accessToken = getAccessToken(),
            ownerId = feedPost.communityId,
            postId = feedPost.id
        )
        _feedPosts.remove(feedPost)
        refreshedListFlow.emit(feedPosts)
    }

    override fun getComments(feedPost: FeedPost): StateFlow<List<Comment>> = flow {
        val comments = vkApiService.getComments(
            accessToken = getAccessToken(),
            ownerId = feedPost.communityId,
            postId = feedPost.id
        )
        emit(feedPostMapper.mapResponseToComments(comments))
    }.retry {
        delay(RETRY_TIMEOUT_MILLIS)
        true
    }.stateIn(
        scope = coroutineScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )

    /**
     * Изменение статуса лайка (установлен или нет) у новостного поста.
     *
     * @param feedPost - новостной пост
     */
    override suspend fun changeLikeStatus(feedPost: FeedPost) {
        // В зависимости от того, какой тек. статус у лайка выбираем метод для добавления/удаления лайка.
        val responseLikes = if (feedPost.isLiked) {
            vkApiService.deleteLike(
                accessToken = getAccessToken(),
                ownerId = feedPost.communityId,
                postId = feedPost.id
            )
        } else {
            vkApiService.addLike(
                accessToken = getAccessToken(),
                ownerId = feedPost.communityId,
                postId = feedPost.id
            )
        }

        // Получаем новые элемент статистики, в котором изменено кол-во лафков.
        val newLikesCount = responseLikes.likes.count
        val newStatistics = feedPost.statistics.toMutableList().apply {
            removeIf { it.type == StatisticType.LIKES }
            add(StatisticPostItem(type = StatisticType.LIKES, newLikesCount))
        }

        // Меняем значение статуса лайков в новостном посте на противоположное.
        val newPost = feedPost.copy(statistics = newStatistics, isLiked = !feedPost.isLiked)
        val postIndex = _feedPosts.indexOf(feedPost)
        _feedPosts[postIndex] = newPost
        refreshedListFlow.emit(feedPosts)
    }

    companion object {

        private const val RETRY_TIMEOUT_MILLIS = 3000L
    }
}