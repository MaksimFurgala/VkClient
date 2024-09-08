package com.example.vkclient.data.model

import com.google.gson.annotations.SerializedName

data class CommentsResponseDataModel(
    @SerializedName("response") val content: CommentsContentDataModel
)
