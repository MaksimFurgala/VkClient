package com.example.vkclient.ui.theme

import com.example.vkclient.domain.FeedPost

sealed class FeedPostScreenState {

    object Initial : FeedPostScreenState()
    data class Posts(val posts: List<FeedPost>) : FeedPostScreenState()
}