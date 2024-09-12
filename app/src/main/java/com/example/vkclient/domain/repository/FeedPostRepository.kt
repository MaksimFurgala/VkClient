package com.example.vkclient.domain.repository

import com.example.vkclient.domain.entity.AuthState
import com.example.vkclient.domain.entity.Comment
import com.example.vkclient.domain.entity.FeedPost
import kotlinx.coroutines.flow.StateFlow

interface FeedPostRepository {

    fun getAuthState(): StateFlow<AuthState>

    fun getRecommendations(): StateFlow<List<FeedPost>>

    fun getComments(feedPost: FeedPost): StateFlow<List<Comment>>

    suspend fun loadNextData()

    suspend fun checkAuthState()

    suspend fun ignoreFeedPost(feedPost: FeedPost)

    suspend fun changeLikeStatus(feedPost: FeedPost)
}