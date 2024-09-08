package com.example.vkclient.data.repository

import android.app.Application
import com.example.vkclient.data.mapper.FeedPostMapper
import com.example.vkclient.data.network.VkApiFactory
import com.example.vkclient.domain.Comment
import com.example.vkclient.domain.FeedPost
import com.example.vkclient.domain.StatisticPostItem
import com.example.vkclient.domain.StatisticType
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken

class FeedPostRepository(application: Application) {

    /**
     * Хранилилище для VK SDK.
     */
    private val storage = VKPreferencesKeyValueStorage(application)

    /**
     * Access-токен для доступа к сервисам VK.
     */
    private val token = VKAccessToken.restore(storage)

    /**
     * Vk api service
     */
    private val vkApiService = VkApiFactory.vkApiService

    /**
     * Feed post mapper
     */
    private val feedPostMapper = FeedPostMapper()

    /**
     * Тек. список новостных постов.
     */
    private val _feedPosts = mutableListOf<FeedPost>()
    val feedPosts: List<FeedPost>
        get() = _feedPosts.toList()

    private var nextFromFeedPost: String? = null

    /**
     * Загрузка новостных постов.
     *
     * @return - список новостных постов
     */
    suspend fun loadFeedPosts(): List<FeedPost> {
        val startFrom = nextFromFeedPost

        if (startFrom == null && feedPosts.isNotEmpty()) return feedPosts

        val response = if (startFrom == null) {
            vkApiService.loadFeedPosts(getAccessToken())
        } else {
            vkApiService.loadFeedPosts(getAccessToken(), startFrom)
        }

        nextFromFeedPost = response.feedPostContent.nextFrom
        val posts = feedPostMapper.mapResponseToFeedPosts(response)
        _feedPosts.addAll(posts)
        return feedPosts
    }

    /**
     * Получение access-токена.
     *
     * @return access-токен
     */
    private fun getAccessToken(): String {
        return token?.accessToken ?: throw IllegalStateException("Access token is null")
    }

    suspend fun ignoreFeedPost(feedPost: FeedPost) {
        vkApiService.ignoreFeedPost(
            accessToken = getAccessToken(),
            ownerId = feedPost.communityId,
            postId = feedPost.id
        )
        _feedPosts.remove(feedPost)
    }

    suspend fun getComments(feedPost: FeedPost):List<Comment> {
        val comments = vkApiService.getComments(
            accessToken = getAccessToken(),
            ownerId = feedPost.communityId,
            postId = feedPost.id
        )
        return feedPostMapper.mapResponseToComments(comments)
    }

    /**
     * Изменение статуса лайка (установлен или нет) у новостного поста.
     *
     * @param feedPost - новостной пост
     */
    suspend fun changeLikeStatus(feedPost: FeedPost) {
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
    }
}