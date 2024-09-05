package com.example.vkclient.domain

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

/**
 * Новостной пост (новости сообществ, друзей и т.д.)
 *
 * @property id - id
 * @property communityId - id сообщества
 * @property communityName - имя автора или сообщества
 * @property publicDate - дата публикации
 * @property avatarResId - id аватарки автора или сообщества TODO:заменить на URL при подключении VK Api
 * @property contentText - содержание поста
 * @property contentImageResId - картинка поста TODO: продумать отображение нескольких картинок
 * @property statistics - элементы статистик (просмотры, share, комментарии и лайки)
 * @constructor Create empty Feed post
 */
@Parcelize
data class FeedPost(
    val id: Long,
    val communityId: Long,
    val communityName: String,
    val publicDate: String,
    val communityImageUrl: String,
    val contentText: String,
    val contentImageUrl: String?,
    val statistics: List<StatisticPostItem>,
    val isLiked: Boolean,
) : Parcelable {
    companion object {
        val NavigationType: NavType<FeedPost> =
            object : NavType<FeedPost>(false) {
                override fun get(bundle: Bundle, key: String): FeedPost? {
                    return bundle.getParcelable(key)
                }

                override fun parseValue(value: String): FeedPost {
                    return Gson().fromJson(value, FeedPost::class.java)
                }

                override fun put(bundle: Bundle, key: String, value: FeedPost) {
                    bundle.putParcelable(key, value)
                }

            }
    }
}