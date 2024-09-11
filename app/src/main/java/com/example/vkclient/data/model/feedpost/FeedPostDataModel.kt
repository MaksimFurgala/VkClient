package com.example.vkclient.data.model.feedpost

import com.google.gson.annotations.SerializedName

/**
 * Новостной пост.
 *
 * @property id - id
 * @property communityId - id сообщества
 * @property text - текст
 * @property date - дата
 * @property likes - лайки
 * @property comments - комментарии
 * @property views - просмотры
 * @property reposts - репосты
 * @property attachments - вложения
 * @constructor Create empty Feed post data model
 */
data class FeedPostDataModel(

    @SerializedName("id")
    val id: Long?,

    @SerializedName("source_id")
    val communityId: Long,

    @SerializedName("text")
    val text: String,

    @SerializedName("date")
    val date: Long,

    @SerializedName("likes")
    val likes: LikesDataModel,

    @SerializedName("comments")
    val comments: CommentsDataModel,

    @SerializedName("views")
    val views: ViewsDataModel,

    @SerializedName("reposts")
    val reposts: RepostsDataModel,

    @SerializedName("attachments")
    val attachments: List<AttachmentDataModel>,
)
