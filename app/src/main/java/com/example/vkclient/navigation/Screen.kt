package com.example.vkclient.navigation

import android.net.Uri
import com.example.vkclient.domain.entity.FeedPost
import com.google.gson.Gson

/**
 * Экран.
 *
 * @property route - маршрут к экрану
 * @constructor Create empty Screen
 */
sealed class Screen(val route: String) {
    object FeedPosts : Screen(ROUTE_FEED_POSTS)
    object Widgets : Screen(ROUTE_WIDGETS)
    object Profile : Screen(ROUTE_PROFILE)
    object Messenger : Screen(ROUTE_MESSENGER)
    object Home : Screen(ROUTE_HOME)
    object Comments : Screen(ROUTE_COMMENTS) {
        private const val ROUTE_FOR_ARGS = "comments"

        /**
         * Получение маршрута с аргументом поста (feedPost).
         *
         * @param feedPost - новостной пост
         * @return - маршрут
         */
        fun getRouteWithArgs(feedPost: FeedPost): String {
            val feedPostJson = Gson().toJson(feedPost)
            return "$ROUTE_FOR_ARGS/${feedPostJson.encode()}"
        }
    }

    companion object {
        const val KEY_FEED_POST = "feed_post"
        const val ROUTE_HOME = "home"
        const val ROUTE_COMMENTS = "comments/{$KEY_FEED_POST}"
        const val ROUTE_FEED_POSTS = "feed_posts"
        const val ROUTE_WIDGETS = "widgets"
        const val ROUTE_PROFILE = "profile"
        const val ROUTE_MESSENGER = "messenger"
    }
}

/**
 * Получить маршрут, как экранированную последовательность.
 *
 * @return
 */
fun String.encode(): String {
    return Uri.encode(this)
}