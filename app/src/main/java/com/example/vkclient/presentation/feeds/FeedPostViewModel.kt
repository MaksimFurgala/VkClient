package com.example.vkclient.presentation.feeds

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkclient.domain.entity.FeedPost
import com.example.vkclient.domain.usecases.feedpost.ChangeLikeStatusUseCase
import com.example.vkclient.domain.usecases.feedpost.GetRecommendationsUseCase
import com.example.vkclient.domain.usecases.feedpost.IgnoreFeedPostUseCase
import com.example.vkclient.domain.usecases.feedpost.LoadNextFeedPostUseCase
import com.example.vkclient.extensions.mergeWith
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View model для постов.
 *
 * @constructor Create empty Feed post view model
 */
@HiltViewModel
class FeedPostViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val getRecommendationsUseCase: GetRecommendationsUseCase,
    private val loadNextFeedPostUseCase: LoadNextFeedPostUseCase,
    private val changeLikeStatusUseCase: ChangeLikeStatusUseCase,
    private val ignoreFeedPostUseCase: IgnoreFeedPostUseCase,
) :
    ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, ex ->
        Log.d("Exception", "${ex.message}")
    }

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