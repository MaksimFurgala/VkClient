package com.example.vkclient.domain.entity

/**
 * Комментарий (общий data класс).
 *
 * @property id - id
 * @property authorName - автор
 * @property authorAvatarUrl - url аватарки
 * @property content - текст комментария
 * @property publicDate - дата публикации
 * @constructor Create empty Comment
 */
data class Comment(
    val id: Long,
    val authorName: String,
    val authorAvatarUrl: String,
    val content: String,
    val publicDate: String,
)
