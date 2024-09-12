package com.example.vkclient.domain.usecases.auth

import com.example.vkclient.domain.repository.FeedPostRepository
import javax.inject.Inject

class CheckAuthStateUseCase @Inject constructor(val repository: FeedPostRepository) {

    suspend operator fun invoke() {
        repository.checkAuthState()
    }
}