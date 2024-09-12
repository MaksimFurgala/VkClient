package com.example.vkclient.presentation.comments

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.vkclient.domain.entity.FeedPost
import com.example.vkclient.domain.usecases.feedpost.GetCommentsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map

/**
 * View model для комментариев.
 *
 * @constructor
 *
 * @param feedPost - новостной пост
 */
@HiltViewModel(assistedFactory = CommentsViewModelFactory::class)
class CommentsViewModel @AssistedInject constructor(
    @Assisted private val feedPost: FeedPost,
    @ApplicationContext val context: Context,
    private val getCommentsUseCase: GetCommentsUseCase,
) : ViewModel() {

    val screenState = getCommentsUseCase(feedPost)
        .map {
            CommentsScreenState.Comments(
                feedPost = feedPost,
                comments = it
            )
        }
}