package com.example.vkclient.domain.usecases.feedpost

import com.example.vkclient.domain.repository.FeedPostRepository
import javax.inject.Inject

class LoadNextFeedPostUseCase @Inject constructor(val repository: FeedPostRepository) {

    suspend operator fun invoke() {
        repository.loadNextData()
    }
}