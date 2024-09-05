package com.example.vkclient.data.model

import com.google.gson.annotations.SerializedName

/**
 * Контент новостного поста.
 *
 * @property posts - посты
 * @property groups - группы
 * @constructor Create empty Feed content data model
 */
data class FeedPostContentDataModel(

    @SerializedName("items")
    val posts: List<FeedPostDataModel>,

    @SerializedName("groups")
    val groups: List<GroupDataModel>,
)
