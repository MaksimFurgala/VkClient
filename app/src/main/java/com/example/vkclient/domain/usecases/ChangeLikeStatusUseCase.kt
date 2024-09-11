package com.example.vkclient.domain.usecases

import com.example.vkclient.domain.entity.FeedPost
import com.example.vkclient.domain.repository.FeedPostRepository

class ChangeLikeStatusUseCase(private val repository: FeedPostRepository) {

    suspend operator fun invoke(feedPost: FeedPost) {
        repository.changeLikeStatus(feedPost)
    }
}