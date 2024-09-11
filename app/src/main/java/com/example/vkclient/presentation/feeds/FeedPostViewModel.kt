package com.example.vkclient.presentation.feeds

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkclient.data.repository.FeedPostRepositoryImpl
import com.example.vkclient.domain.entity.FeedPost
import com.example.vkclient.domain.usecases.ChangeLikeStatusUseCase
import com.example.vkclient.domain.usecases.GetRecommendationsUseCase
import com.example.vkclient.domain.usecases.IgnoreFeedPostUseCase
import com.example.vkclient.domain.usecases.LoadNextFeedPostUseCase
import com.example.vkclient.extensions.mergeWith
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

/**
 * View model для постов.
 *
 * @constructor Create empty Feed post view model
 */
class FeedPostViewModel(application: Application) : AndroidViewModel(application) {

    private val exceptionHandler = CoroutineExceptionHandler { _, ex ->
        Log.d("Exception", "${ex.message}")
    }

    private val repository = FeedPostRepositoryImpl(application)

    private val getRecommendationsUseCase = GetRecommendationsUseCase(repository)
    private val loadNextFeedPostUseCase = LoadNextFeedPostUseCase(repository)
    private val changeLikeStatusUseCase = ChangeLikeStatusUseCase(repository)
    private val ignoreFeedPostUseCase = IgnoreFeedPostUseCase(repository)

    private val feedPostsFlow = getRecommendationsUseCase()

    private val loadNextDataFlow = MutableSharedFlow<FeedPostScreenState>()

    // State для поста
    val screenState = feedPostsFlow
        .filter { it.isNotEmpty() }
        .map { FeedPostScreenState.Posts(posts = it) as FeedPostScreenState }
        .onStart { emit(FeedPostScreenState.Loading) }
        .mergeWith(loadNextDataFlow)

    /**
     * Загрузка следующих постов (подгрузка).
     *
     */
    fun loadNextFeedPosts() {
        viewModelScope.launch {
            loadNextDataFlow.emit(
                FeedPostScreenState.Posts(
                    posts = feedPostsFlow.value,
                    nextFeedPostsIsLoading = true
                )
            )
            loadNextFeedPostUseCase()
        }
    }

    /**
     * Изменение статуса лафка.
     *
     * @param feedPost - новостной пост
     */
    fun changeLikeStatus(feedPost: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            changeLikeStatusUseCase(feedPost)
        }
    }

    /**
     * Удаление поста.
     * TODO предусмотреть удаление только своих постов, чужие посты будут просто удалены из списка.
     *
     * @param feedPost - пост
     */
    fun remove(feedPost: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            ignoreFeedPostUseCase(feedPost)
        }
    }
}