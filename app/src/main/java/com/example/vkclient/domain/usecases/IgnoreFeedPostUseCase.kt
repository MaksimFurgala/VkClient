package com.example.vkclient.domain.usecases

import com.example.vkclient.domain.entity.FeedPost
import com.example.vkclient.domain.repository.FeedPostRepository

class IgnoreFeedPostUseCase(private val repository: FeedPostRepository) {

    suspend operator fun invoke(feedPost: FeedPost) {
        repository.ignoreFeedPost(feedPost)
    }
}