package com.example.vkclient.data.model.feedpost

import com.google.gson.annotations.SerializedName

/**
 * Фото.
 *
 * @property photoCollection - коллекция фото
 * @constructor Create empty Photo data model
 */
data class PhotoDataModel(

    @SerializedName("sizes")
    val photoCollection: List<PhotoInfoDataModel>
)
