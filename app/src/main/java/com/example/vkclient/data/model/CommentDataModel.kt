package com.example.vkclient.data.model

import com.google.gson.annotations.SerializedName

data class CommentDataModel(
    @SerializedName("id") val id: Long,
    @SerializedName("from_id") val authorId: Long,
    @SerializedName("text") val text: String,
    @SerializedName("date") val date: Long
)
