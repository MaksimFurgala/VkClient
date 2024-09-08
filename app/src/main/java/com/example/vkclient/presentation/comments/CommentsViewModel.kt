package com.example.vkclient.presentation.comments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.vkclient.data.repository.FeedPostRepository
import com.example.vkclient.domain.Comment
import com.example.vkclient.domain.FeedPost
import kotlinx.coroutines.launch

/**
 * View model для комментариев.
 *
 * @constructor
 *
 * @param feedPost - новостной пост
 */
class CommentsViewModel(
    feedPost: FeedPost,
    application: Application
) : AndroidViewModel(application) {

    private val repository = FeedPostRepository(application)

    private val _screenState = MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState: LiveData<CommentsScreenState> = _screenState

    init {
        loadComments(feedPost)
    }

    /**
     * Загрузка комментариев для выбранного поста.
     * TODO: сделать комментарии для других объектов
     *
     * @param feedPost - новостной пост
     */
    private fun loadComments(feedPost: FeedPost) {
        viewModelScope.launch {
            val comments = repository.getComments(feedPost)
            _screenState.value = CommentsScreenState.Comments(
                feedPost = feedPost,
                comments = comments
            )
        }
    }
}