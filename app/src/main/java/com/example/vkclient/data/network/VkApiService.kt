package com.example.vkclient.data.network

import com.example.vkclient.data.model.feedpost.CommentsResponseDataModel
import com.example.vkclient.data.model.feedpost.FeedResponseDataModel
import com.example.vkclient.data.model.feedpost.LikesCountResponseDataModel
import com.example.vkclient.data.model.profile.ProfileResponseDataModel
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Vk api сервис.
 *
 * @constructor Create empty Vk api service
 */
interface VkApiService {

    @GET("users.get?v=5.199&field=bdate")
    suspend fun getUserProfile(@Query("access_token") accessToken: String): ProfileResponseDataModel

    /**
     * Загрузка новостных постов.
     *
     * @param accessToken - accessToken
     * @return - новостные посты
     */
    @GET("newsfeed.getRecommended?v=5.199")
    suspend fun loadFeedPosts(@Query("access_token") accessToken: String): FeedResponseDataModel

    @GET("newsfeed.getRecommended?v=5.199")
    suspend fun loadFeedPosts(
        @Query("access_token") accessToken: String,
        @Query("start_from") startFrom: String
    ): FeedResponseDataModel

    @GET("newsfeed.ignoreItem?v=5.199&type=post")
    suspend fun ignoreFeedPost(
        @Query("access_token") accessToken: String,
        @Query("owner_id") ownerId: Long,
        @Query("item_id") postId: Long,
    )

    @GET("wall.getComments?v=5.199&extended=1&fields=photo_100")
    suspend fun getComments(
        @Query("access_token") accessToken: String,
        @Query("owner_id") ownerId: Long,
        @Query("post_id") postId: Long
    ): CommentsResponseDataModel

    /**
     * Добавление лайков.
     *
     * @param accessToken - accessToken
     * @param ownerId - id владельца поста
     * @param postId - id поста
     * @return - лайки
     */
    @GET("likes.add?v=5.199&type=post")
    suspend fun addLike(
        @Query("access_token") accessToken: String,
        @Query("owner_id") ownerId: Long,
        @Query("item_id") postId: Long,
    ): LikesCountResponseDataModel

    /**
     * Удаление лайков.
     *
     * @param accessToken - accessToken
     * @param ownerId - id владельца поста
     * @param postId - id поста
     * @return
     */
    @GET("likes.delete?v=5.199&type=post")
    suspend fun deleteLike(
        @Query("access_token") accessToken: String,
        @Query("owner_id") ownerId: Long,
        @Query("item_id") postId: Long,
    ): LikesCountResponseDataModel
}