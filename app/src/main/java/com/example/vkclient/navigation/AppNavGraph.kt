package com.example.vkclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vkclient.domain.entity.FeedPost

/**
 * Основной граф навигации приложения.
 *
 * @param navHostController - контроллер навигации
 * @param feedScreenContent - экран со списком постов
 * @param favouriteScreenContent - экран с избранным контентом
 * @param messengerScreenContent - мессенджер
 * @param profileScreenContent - профиль пользователя
 * @param commentsScreenContent - экран со списком комментариев
 */
@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    feedScreenContent: @Composable () -> Unit,
    favouriteScreenContent: @Composable () -> Unit,
    messengerScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (FeedPost) -> Unit
) {
    NavHost(navController = navHostController, startDestination = Screen.Home.route) {

        // Главная.
        homeScreenNavGraph(
            feedScreenContent = feedScreenContent,
            commentsScreenContent = commentsScreenContent
        )

        // Избранное
        composable(Screen.Favourite.route) {
            favouriteScreenContent()
        }

        // Мессенджер
        composable(Screen.Messenger.route) {
            messengerScreenContent()
        }

        // Профиль
        composable(Screen.Profile.route) {
            profileScreenContent()
        }
    }
}