package com.example.vkclient.data.model

import com.google.gson.annotations.SerializedName

/**
 * Вложение (фото).
 *
 * @property photo
 * @constructor Create empty Attachment data model
 */
data class AttachmentDataModel(

    @SerializedName("photo")
    val photo: PhotoDataModel
)
