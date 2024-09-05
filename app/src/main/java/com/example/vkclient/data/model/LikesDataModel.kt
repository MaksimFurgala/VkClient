package com.example.vkclient.data.model

import com.google.gson.annotations.SerializedName

/**
 * Лайки.
 *
 * @property count - общее кол-во лайков
 * @property userLikes - кол-во лайков пользователя
 * @constructor Create empty Likes data model
 */
data class LikesDataModel(

    @SerializedName("count")
    val count: Int = 0,

    @SerializedName("user_likes")
    val userLikes: Int,
)
