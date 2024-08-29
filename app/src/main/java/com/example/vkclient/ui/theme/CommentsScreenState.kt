package com.example.vkclient.ui.theme

import com.example.vkclient.domain.Comment
import com.example.vkclient.domain.FeedPost

sealed class CommentsScreenState {

    object Initial : CommentsScreenState()
    data class Comments(
        val feedPost: FeedPost,
        val comments: List<Comment>
    ) : CommentsScreenState()
}