package com.example.vkclient.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vkclient.domain.entity.FeedPost

/**
 * State для навигации
 *
 * @property navHostController - контроллер навигации
 * @constructor Create empty Navigation state
 */
class NavigationState(
    val navHostController: NavHostController
) {

    /**
     * Навигация к указанному маршруту + настройка поведения.
     *
     * @param route - маршрут
     */
    fun navigateTo(route: String) {
        navHostController.navigate(route) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    /**
     * Навигация к экрану с комментариями.
     *
     * @param feedPost - новостной пост.
     */
    fun navigateToComments(feedPost: FeedPost) {
        navHostController.navigate(Screen.Comments.getRouteWithArgs(feedPost))
    }
}

/**
 * Получение state'а навигации через контроллер навигации.
 *
 * @param navHostController - контроллер навигации
 * @return state навигации
 */
@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState {
    return remember {
        NavigationState(navHostController)
    }
}