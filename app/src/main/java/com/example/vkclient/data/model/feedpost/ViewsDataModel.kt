package com.example.vkclient.data.model.feedpost

import com.google.gson.annotations.SerializedName

/**
 * Просмотры.
 *
 * @property count - количество просмотров
 * @constructor Create empty Views data model
 */
data class ViewsDataModel(

    @SerializedName("count")
    val count: Int = 0,
)
