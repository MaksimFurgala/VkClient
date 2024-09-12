package com.example.vkclient.data.model.feedpost

import com.google.gson.annotations.SerializedName

data class CommentsResponseDataModel(
    @SerializedName("response") val content: CommentsContentDataModel,
)
