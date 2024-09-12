package com.example.vkclient.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.vkclient.R
import com.example.vkclient.navigation.Screen

sealed class NavigationItem(
    val screen: Screen,
    val titleResId: Int,
    val icon: ImageVector,
) {
    object Home :
        NavigationItem(
            screen = Screen.Home,
            titleResId = R.string.navigation_item_home,
            icon = Icons.Outlined.Home
        )

    object Favourite : NavigationItem(
        screen = Screen.Favourite,
        titleResId = R.string.navigation_item_favourite,
        icon = Icons.Outlined.Favorite
    )

    object Message : NavigationItem(
        screen = Screen.Messenger,
        titleResId = R.string.navigation_item_messages,
        icon = Icons.Outlined.Email
    )

    object Profile : NavigationItem(
        screen = Screen.Profile,
        titleResId = R.string.navigation_item_profile,
        icon = Icons.Outlined.Person
    )
}