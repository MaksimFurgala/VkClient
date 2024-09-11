package com.example.vkclient.data.mapper

import com.example.vkclient.commons.DateConverters
import com.example.vkclient.data.model.CommentsResponseDataModel
import com.example.vkclient.data.model.FeedResponseDataModel
import com.example.vkclient.domain.entity.Comment
import com.example.vkclient.domain.entity.FeedPost
import com.example.vkclient.domain.entity.StatisticPostItem
import com.example.vkclient.domain.entity.StatisticType
import kotlin.math.absoluteValue

/**
 * Мапер для новостных постов.
 *
 * @constructor Create empty Feed post mapper
 */
class FeedPostMapper {

    /**
     * Мапер FeedResponseDataModel в список новостных постов.
     *
     * @param responseDataModel - ответ от REST Api сервиса VK
     * @return список новостных постов
     */
    fun mapResponseToFeedPosts(responseDataModel: FeedResponseDataModel): List<FeedPost> {
        val result = mutableListOf<FeedPost>()
        val posts = responseDataModel.feedPostContent.posts
        val groups = responseDataModel.feedPostContent.groups

        posts.forEach posts@{ post ->

            // Отбираем посты у которых есть id и у которых есть ссылки на сообщества.
            if (post.id == null) return@posts
            val group = groups.find { it.id == post.communityId.absoluteValue } ?: return@posts

            val feedPost = FeedPost(
                id = post.id,
                communityId = post.communityId,
                communityName = group.name,
                publicDate = DateConverters.timestampToStringDate(post.date),
                communityImageUrl = group.photoUrl,
                contentText = post.text,
                contentImageUrl = post.attachments.firstOrNull()?.photo?.photoCollection?.lastOrNull()?.url,
                isLiked = post.likes.userLikes > 0,
                statistics = listOf(
                    StatisticPostItem(StatisticType.LIKES, post.likes.count),
                    StatisticPostItem(StatisticType.COMMENTS, post.comments.count),
                    StatisticPostItem(StatisticType.SHARES, post.reposts.count),
                    StatisticPostItem(StatisticType.VIEWS, post.views.count)
                )
            )
            result.add(feedPost)
        }
        return result
    }

    fun mapResponseToComments(response: CommentsResponseDataModel): List<Comment> {
        val result = mutableListOf<Comment>()
        val comments = response.content.comments
        val profiles = response.content.profiles

        comments.forEach eachComment@{ comment ->
            if (comment.text.isBlank()) return@eachComment //TODO: реализовать наполнение карточки комментария, если контент содержит только медиа (картинки, видео, гифки и т.д.)
            val author = profiles.firstOrNull { it.id == comment.authorId } ?: return@eachComment
            val postComment = Comment(
                id = comment.id,
                authorName = "${author.firstName} ${author.lastName}",
                authorAvatarUrl = author.avatarUrl,
                content = comment.text,
                publicDate = DateConverters.timestampToStringDate(comment.date)
            )
            result.add(postComment)
        }
        return result
    }
}