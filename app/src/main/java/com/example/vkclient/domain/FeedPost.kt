package com.example.vkclient.domain

import com.example.vkclient.R

/**
 * Новостной пост (новости сообществ, друзей и т.д.)
 *
 * @property id - id
 * @property groupName - имя автора или сообщества
 * @property publicDate - дата публикации
 * @property avatarResId - id аватарки автора или сообщества TODO:заменить на URL при подключении VK Api
 * @property contentText - содержание поста
 * @property contentImageResId - картинка поста TODO: продумать отображение нескольких картинок
 * @property statistics - элементы статистик (просмотры, share, комментарии и лайки)
 * @constructor Create empty Feed post
 */
data class FeedPost(
    val id: Int = 0,
    val groupName: String = "Android Broadcast",
    val publicDate: String = "13:00",
    val avatarResId: Int = R.drawable.icon_post,
    val contentText: String = "Lorem ipsum odor amet, consectetuer adipiscing elit. Cubilia cubilia consequat porta viverra non tristique accumsan facilisi.",
    val contentImageResId: Int = R.drawable.post_image,
    val statistics: List<StatisticPostItem> = listOf(
        StatisticPostItem(StatisticType.VIEWS, 133),
        StatisticPostItem(StatisticType.SHARES, 3),
        StatisticPostItem(StatisticType.COMMENTS, 2),
        StatisticPostItem(StatisticType.LIKES, 1)
    )
)