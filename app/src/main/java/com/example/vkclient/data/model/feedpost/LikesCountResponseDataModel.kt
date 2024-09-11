package com.example.vkclient.data.model.feedpost

import com.google.gson.annotations.SerializedName

/**
 * Ответ REST Api для кол-ва лайков.
 *
 * @property likes - лайки
 * @constructor Create empty Likes count response data model
 */
data class LikesCountResponseDataModel(

    @SerializedName("response") val likes: LikesCountDataModel
)
