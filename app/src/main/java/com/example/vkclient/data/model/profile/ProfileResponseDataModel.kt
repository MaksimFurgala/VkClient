package com.example.vkclient.data.model.profile

import com.google.gson.annotations.SerializedName

data class ProfileResponseDataModel(
    @SerializedName("response")
    val profile: ProfileDataModel,
)