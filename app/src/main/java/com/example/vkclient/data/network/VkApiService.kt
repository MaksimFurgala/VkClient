package com.example.vkclient.data.network

import com.example.vkclient.data.model.FeedResponseDataModel
import com.example.vkclient.data.model.LikesCountResponseDataModel
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Vk api сервис.
 *
 * @constructor Create empty Vk api service
 */
interface VkApiService {

    /**
     * Загрузка новостных постов.
     *
     * @param accessToken - accessToken
     * @return - новостные посты
     */
    @GET("newsfeed.get?v=5.199")
    suspend fun loadPosts(@Query("access_token") accessToken: String): FeedResponseDataModel

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