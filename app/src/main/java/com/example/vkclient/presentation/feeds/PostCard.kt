package com.example.vkclient.presentation.feeds

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.vkclient.R
import com.example.vkclient.domain.entity.FeedPost
import com.example.vkclient.domain.entity.StatisticPostItem
import com.example.vkclient.domain.entity.StatisticType
import com.example.vkclient.ui.theme.darkRed
import java.util.Locale

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    feedPost: FeedPost,
    onLikeClickListener: (StatisticPostItem) -> Unit,
    onCommentClickListener: (StatisticPostItem) -> Unit,
) {
    Card(modifier = modifier) {
        HeaderPostCard(feedPost)
        Text(
            text = feedPost.contentText,
            modifier = Modifier.padding(top = 8.dp)
        )
        AsyncImage(
            model = feedPost.contentImageUrl,
            contentDescription = "",
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            contentScale = ContentScale.FillWidth
        )
        BottomBarPostCard(
            statistics = feedPost.statistics,
            onLikeClickListener = onLikeClickListener,
            onCommentClickListener = onCommentClickListener,
            isFavourite = feedPost.isLiked
        )
    }
}

@Composable
private fun HeaderPostCard(
    feedPost: FeedPost,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = feedPost.communityImageUrl,
            contentDescription = "",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            Text(text = feedPost.communityName, color = MaterialTheme.colorScheme.onPrimary)
            Text(text = feedPost.publicDate, color = MaterialTheme.colorScheme.onSecondary)
        }
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = "",
            modifier = Modifier.padding(start = 8.dp),
            tint = MaterialTheme.colorScheme.onSecondary
        )
    }
}

@Composable
private fun BottomBarPostCard(
    statistics: List<StatisticPostItem>,
    onLikeClickListener: (StatisticPostItem) -> Unit,
    onCommentClickListener: (StatisticPostItem) -> Unit,
    isFavourite: Boolean
) {
    Row {
        Row(
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
        ) {
            val viewsItem = statistics.getItemByType(StatisticType.VIEWS)
            IconWithText(
                iconResId = R.drawable.ic_views_count,
                text = formatStatisticPostItemCount(viewsItem.count)
            )
        }
        Row(
            modifier = Modifier
                .weight(1f)
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val sharesItem = statistics.getItemByType(StatisticType.SHARES)
            IconWithText(
                iconResId = R.drawable.ic_share,
                text = formatStatisticPostItemCount(sharesItem.count)
            )
            val commentsItem = statistics.getItemByType(StatisticType.COMMENTS)
            IconWithText(
                iconResId = R.drawable.ic_comment,
                text = formatStatisticPostItemCount(commentsItem.count),
                onItemClickListener = {
                    onCommentClickListener(commentsItem)
                }
            )
            val likesItem = statistics.getItemByType(StatisticType.LIKES)
            IconWithText(
                iconResId = if (isFavourite) R.drawable.ic_like_set else R.drawable.ic_like,
                text = formatStatisticPostItemCount(likesItem.count),
                onItemClickListener = {
                    onLikeClickListener(likesItem)
                },
                tint = if (isFavourite) darkRed else MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}

private fun List<StatisticPostItem>.getItemByType(type: StatisticType): StatisticPostItem {
    return this.find { it.type == type } ?: throw IllegalStateException()
}

private fun formatStatisticPostItemCount(count: Int): String {
    return if (count > 100_000) {
        String.format(Locale.getDefault(), "%sK", count / 1000)
    } else if (count > 1000) {
        String.format(Locale.getDefault(), "%.1fK", count / 1000f)
    } else {
        count.toString()
    }
}

@Composable
private fun IconWithText(
    iconResId: Int,
    text: String,
    onItemClickListener: (() -> Unit)? = null,
    tint: Color = MaterialTheme.colorScheme.onSecondary
) {
    val modifier = if (onItemClickListener == null) {
        Modifier
    } else {
        Modifier.clickable {
            onItemClickListener()
        }
    }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = tint
        )
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(4.dp)
        )
    }
}