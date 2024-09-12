package com.example.vkclient.domain.usecases.feedpost

import com.example.vkclient.domain.entity.Comment
import com.example.vkclient.domain.entity.FeedPost
import com.example.vkclient.domain.repository.FeedPostRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(val repository: FeedPostRepository) {

    operator fun invoke(feedPost: FeedPost): StateFlow<List<Comment>> {
        return repository.getComments(feedPost)
    }
}