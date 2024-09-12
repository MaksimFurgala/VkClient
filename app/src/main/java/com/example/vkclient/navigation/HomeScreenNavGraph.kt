package com.example.vkclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.vkclient.domain.entity.FeedPost

/**
 * Вложенный граф для вкладки "Главная".
 *
 * @param feedScreenContent - экран с постами
 * @param commentsScreenContent - экран с комментариями
 *
 */
fun NavGraphBuilder.homeScreenNavGraph(
    feedScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (FeedPost) -> Unit,
) {
    navigation(
        startDestination = Screen.FeedPosts.route,
        route = Screen.Home.route
    ) {

        // Посты.
        composable(Screen.FeedPosts.route) {
            feedScreenContent()
        }

        // Комментарии.
        composable(
            route = Screen.Comments.route,
            arguments = listOf(navArgument(Screen.KEY_FEED_POST) {
                type = FeedPost.NavigationType
            })
        ) {
            // Получаем из бекстека аргумент с постом в виде json и парсим в объект FeedPost.
            val feedPost = it.arguments?.getParcelable<FeedPost>(Screen.KEY_FEED_POST)
                ?: throw RuntimeException("Args is null")

            commentsScreenContent(feedPost)
        }
    }
}