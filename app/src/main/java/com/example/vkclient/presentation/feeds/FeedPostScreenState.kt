package com.example.vkclient.presentation.feeds

import com.example.vkclient.domain.FeedPost

sealed class FeedPostScreenState {

    object Initial : FeedPostScreenState()
    data class Posts(val posts: List<FeedPost>) : FeedPostScreenState()
}