package com.example.vkclient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vkclient.domain.FeedPost

/**
 * Фабрика для создания viewmodel'и экрана комментариев.
 *
 * @property feedPost - новостной пост
 * @constructor Create empty Comments view model factory
 */
class CommentsViewModelFactory(
    val feedPost: FeedPost
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CommentsViewModel(feedPost) as T
    }
}