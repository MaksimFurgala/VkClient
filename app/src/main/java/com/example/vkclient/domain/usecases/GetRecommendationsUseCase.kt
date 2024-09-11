package com.example.vkclient.domain.usecases

import com.example.vkclient.domain.entity.FeedPost
import com.example.vkclient.domain.repository.FeedPostRepository
import kotlinx.coroutines.flow.StateFlow

class GetRecommendationsUseCase(private val repository: FeedPostRepository) {

    operator fun invoke(): StateFlow<List<FeedPost>> {
        return repository.getRecommendations()
    }
}