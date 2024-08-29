package com.example.vkclient.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vkclient.CommentsViewModel
import com.example.vkclient.CommentsViewModelFactory
import com.example.vkclient.domain.Comment
import com.example.vkclient.domain.FeedPost

/**
 * Экран со списком комментариев.
 *
 * @param feedPost - новостной пост
 * @param onBackPressed - колбек для кнопки назад
 * @receiver
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentsScreen(
    feedPost: FeedPost,
    onBackPressed: () -> Unit,
) {
    // ViewModel для комментариев.
    val viewModel: CommentsViewModel = viewModel(factory = CommentsViewModelFactory(feedPost))
    // State для экрана комментариев.
    val screenState = viewModel.screenState.observeAsState(CommentsScreenState.Initial)
    // Текущий state.
    val currentState = screenState.value

    if (currentState is CommentsScreenState.Comments) {
        Scaffold(topBar = {
            TopAppBar(title = { Text(text = "Комментарии") },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
                    }
                })
        }) { paddingValues ->
            LazyColumn(
                modifier = Modifier.padding(paddingValues),
                contentPadding = PaddingValues(
                    top = 16.dp,
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 64.dp
                )
            ) {
                items(items = currentState.comments, { it.id }) { comment ->
                    CommentItem(comment = comment)
                }
            }
        }
    }
}

/**
 * Элемент для lazyColumn с комментариями.
 *
 * @param comment - комментарий
 */
@Composable
private fun CommentItem(comment: Comment) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        // Аватарка автора комментария.
        Image(
            modifier = Modifier.size(24.dp).clip(CircleShape),
            painter = painterResource(id = comment.authorAvatarId),
            contentDescription = null
        )

        // Основное содержимое.
        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            // Имя комментатора.
            Text(
                text = "${comment.authorName} CommentId: ${comment.id}",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 12.sp
            )
            // Текст комментария.
            Text(
                modifier = Modifier.padding(vertical = 4.dp),
                text = comment.content,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 14.sp
            )
            // Дата и время написания комментария
            Text(
                modifier = Modifier.padding(vertical = 4.dp),
                text = comment.publicDate,
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = 12.sp
            )
        }
    }
}

/**
 * Превью для комментария (DarkTheme).
 *
 */
@Preview
@Composable
private fun PreviewCommentDark() {
    VkClientTheme(dynamicColor = false, darkTheme = true) {
        CommentItem(comment = Comment(id = 1))
    }
}

/**
 * Превью для комментария (LightTheme).
 *
 */
@Preview
@Composable
private fun PreviewCommentLight() {
    VkClientTheme(dynamicColor = false, darkTheme = false) {
        CommentItem(comment = Comment(id = 1))
    }
}