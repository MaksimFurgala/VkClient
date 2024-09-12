package com.example.vkclient.presentation.comments

import com.example.vkclient.domain.entity.FeedPost
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory

/**
 * Фабрика для создания viewmodel'и экрана комментариев.
 *
 * @constructor Create empty Comments view model factory
 */
@AssistedFactory
interface CommentsViewModelFactory {
    fun create(@Assisted feedPost: FeedPost): CommentsViewModel
}