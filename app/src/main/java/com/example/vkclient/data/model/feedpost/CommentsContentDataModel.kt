package com.example.vkclient.data.model.feedpost

import com.google.gson.annotations.SerializedName

data class CommentsContentDataModel(
    @SerializedName("items") val comments: List<CommentDataModel>,
    @SerializedName("profiles") val profiles: List<ProfileDataModel>,
)
