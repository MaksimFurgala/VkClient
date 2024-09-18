package com.example.vkclient.data.model.feedpost

import com.google.gson.annotations.SerializedName

data class ProfileDataModel(
    @SerializedName("id")
    val id: Long,

    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String,

    @SerializedName("photo_100")
    val avatarUrl: String,

    @SerializedName("bdate")
    val birthday: String,
)
