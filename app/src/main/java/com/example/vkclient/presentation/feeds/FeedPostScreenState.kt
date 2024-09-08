package com.example.vkclient.presentation.feeds

import com.example.vkclient.domain.FeedPost

sealed class FeedPostScreenState {

    object Initial : FeedPostScreenState()

    object Loading : FeedPostScreenState()

    data class Posts(
        val posts: List<FeedPost>,
        val nextFeedPostsIsLoading: Boolean = false
    ) :
        FeedPostScreenState()
}