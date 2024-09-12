package com.example.vkclient.data.model.feedpost

import com.google.gson.annotations.SerializedName

/**
 * Комментарии.
 *
 * @property count - кол-во комментариев.
 * @constructor Create empty Comments data model
 */
data class CommentsDataModel(

    @SerializedName("count")
    val count: Int = 0,
)
