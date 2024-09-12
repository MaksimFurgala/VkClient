package com.example.vkclient.domain.usecases.auth

import com.example.vkclient.domain.entity.AuthState
import com.example.vkclient.domain.repository.FeedPostRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetAuthStateUseCase @Inject constructor(val repository: FeedPostRepository) {

    operator fun invoke(): StateFlow<AuthState> {
        return repository.getAuthState()
    }
}