package com.example.vkclient.data.model.profile

import com.google.gson.annotations.SerializedName

data class ProfileDataModel(

    @SerializedName("id")
    val id: Float,

    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String,

    @SerializedName("bdate")
    val birthday: String,
)