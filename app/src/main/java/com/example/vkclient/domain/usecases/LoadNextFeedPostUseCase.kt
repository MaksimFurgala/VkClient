package com.example.vkclient.domain.usecases

import com.example.vkclient.domain.entity.AuthState
import com.example.vkclient.domain.repository.FeedPostRepository
import kotlinx.coroutines.flow.StateFlow

class LoadNextFeedPostUseCase(private val repository: FeedPostRepository) {

    suspend operator fun invoke() {
        repository.loadNextData()
    }
}