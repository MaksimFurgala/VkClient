package com.example.vkclient.presentation.comments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.vkclient.data.repository.FeedPostRepositoryImpl
import com.example.vkclient.domain.entity.FeedPost
import com.example.vkclient.domain.usecases.GetCommentsUseCase
import kotlinx.coroutines.flow.map

/**
 * View model для комментариев.
 *
 * @constructor
 *
 * @param feedPost - новостной пост
 */
class CommentsViewModel(
    feedPost: FeedPost,
    application: Application,
) : AndroidViewModel(application) {

    private val repository = FeedPostRepositoryImpl(application)

    private val getCommentsUseCase = GetCommentsUseCase(repository)

    val screenState = getCommentsUseCase(feedPost)
        .map {
            CommentsScreenState.Comments(
                feedPost = feedPost,
                comments = it
            )
        }
}