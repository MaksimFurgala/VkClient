package com.example.vkclient.domain.usecases

import com.example.vkclient.domain.entity.Comment
import com.example.vkclient.domain.entity.FeedPost
import com.example.vkclient.domain.repository.FeedPostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class GetCommentsUseCase(private val repository: FeedPostRepository) {

    operator fun invoke(feedPost: FeedPost): StateFlow<List<Comment>> {
        return repository.getComments(feedPost)
    }
}