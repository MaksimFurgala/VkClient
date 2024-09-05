package com.example.vkclient.data.model

import com.google.gson.annotations.SerializedName

/**
 * Группа (сообщество).
 *
 * @property id - id
 * @property name - наименование
 * @property photoUrl - url фото (иконка сообщества)
 * @constructor Create empty Group data model
 */
data class GroupDataModel(

    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("photo_200")
    val photoUrl: String,
)