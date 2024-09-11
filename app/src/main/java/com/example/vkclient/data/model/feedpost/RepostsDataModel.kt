package com.example.vkclient.data.model.feedpost

import com.google.gson.annotations.SerializedName

/**
 * Репосты.
 *
 * @property count - ко-во
 * @constructor Create empty Reposts data model
 */
data class RepostsDataModel(

    @SerializedName("count")
    val count: Int = 0
)
