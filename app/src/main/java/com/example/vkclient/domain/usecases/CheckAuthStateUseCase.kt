package com.example.vkclient.domain.usecases

import com.example.vkclient.domain.repository.FeedPostRepository

class CheckAuthStateUseCase(private val repository: FeedPostRepository) {

    suspend operator fun invoke() {
        repository.checkAuthState()
    }
}