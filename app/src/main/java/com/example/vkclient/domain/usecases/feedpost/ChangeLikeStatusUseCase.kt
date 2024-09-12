package com.example.vkclient.domain.usecases.feedpost

import com.example.vkclient.domain.entity.FeedPost
import com.example.vkclient.domain.repository.FeedPostRepository
import javax.inject.Inject

class ChangeLikeStatusUseCase @Inject constructor(val repository: FeedPostRepository) {

    suspend operator fun invoke(feedPost: FeedPost) {
        repository.changeLikeStatus(feedPost)
    }
}