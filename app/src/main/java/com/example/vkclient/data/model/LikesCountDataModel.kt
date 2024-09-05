package com.example.vkclient.data.model

import com.google.gson.annotations.SerializedName

/**
 * Лайки.
 *
 * @property count - кол-во
 * @constructor Create empty Likes count data model
 */
data class LikesCountDataModel(

    @SerializedName("likes") val count: Int,
)