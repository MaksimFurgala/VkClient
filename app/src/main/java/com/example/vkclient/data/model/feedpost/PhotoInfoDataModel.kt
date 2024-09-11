package com.example.vkclient.data.model.feedpost

import com.google.gson.annotations.SerializedName

/**
 * Доп. информация по фото.
 *
 * @property url - url фото
 * @constructor Create empty Photo info data model
 */
data class PhotoInfoDataModel(

    @SerializedName("url")
    val url: String,
)
