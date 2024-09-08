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
    val id: Long,
    val authorName: String,
    val authorAvatarUrl: String,
    val content: String,
    val publicDate: String
)
