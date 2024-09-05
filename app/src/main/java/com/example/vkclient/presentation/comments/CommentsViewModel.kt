package com.example.vkclient.presentation.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vkclient.domain.Comment
import com.example.vkclient.domain.FeedPost

/**
 * View model для комментариев.
 *
 * @constructor
 *
 * @param feedPost - новостной пост
 */
class CommentsViewModel(
    feedPost: FeedPost
) : ViewModel() {

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
        val comments = mutableListOf<Comment>().apply {
            repeat(10) {
                add(Comment(id = it))
            }
        }
        _screenState.value = CommentsScreenState.Comments(
            feedPost = feedPost,
            comments = comments
        )
    }
}