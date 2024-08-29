package com.example.vkclient.domain

import com.example.vkclient.R

/**
 * Комментарий (общий data класс).
 *
 * @property id - id
 * @property authorName - автор
 * @property authorAvatarId - id аватарки.TODO:заменить на URL при подключении VK Api
 * @property content - текст комментария
 * @property publicDate - дата публикации
 * @constructor Create empty Comment
 */
data class Comment(
    val id: Int,
    val authorName: String = "John Devid Washington",
    val authorAvatarId: Int = R.drawable.icon_post,
    val content: String = "Loren ipsum dollar then ...",
    val publicDate: String = "12:00"
)
