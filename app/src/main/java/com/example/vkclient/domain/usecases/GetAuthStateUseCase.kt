package com.example.vkclient.domain.usecases

import com.example.vkclient.domain.entity.AuthState
import com.example.vkclient.domain.repository.FeedPostRepository
import kotlinx.coroutines.flow.StateFlow

class GetAuthStateUseCase(private val repository: FeedPostRepository) {

    operator fun invoke(): StateFlow<AuthState> {
        return repository.getAuthState()
    }
}