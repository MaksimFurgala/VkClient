package com.example.vkclient.presentation.comments

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.vkclient.domain.entity.Comment
import com.example.vkclient.domain.entity.FeedPost

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
    val viewModel = hiltViewModel<CommentsViewModel, CommentsViewModelFactory>(
        creationCallback = { it.create(feedPost) }
    )

    // State для экрана комментариев.
    val screenState = viewModel.screenState.collectAsState(CommentsScreenState.Initial)

    // Текущий state.
    val currentState = screenState.value

    if (currentState is CommentsScreenState.Comments) {
        Scaffold(topBar = {
            TopAppBar(title = { Text(text = "Комментарии") },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
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
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
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
        AsyncImage(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            model = comment.authorAvatarUrl,
            contentDescription = null
        )

        // Основное содержимое.
        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            // Имя комментатора.
            Text(
                text = "${comment.authorName}",
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
