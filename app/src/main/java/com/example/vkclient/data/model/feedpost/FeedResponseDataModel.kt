package com.example.vkclient.data.model.feedpost

import com.google.gson.annotations.SerializedName

/**
 * Ответ REST Api для новостного поста.
 *
 * @property feedPostContent - контент новостного поста
 * @constructor Create empty Feed response data model
 */
data class FeedResponseDataModel(

    @SerializedName("response")
    val feedPostContent: FeedPostContentDataModel,
)
