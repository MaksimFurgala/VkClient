package com.example.vkclient.ui.theme

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.sharp.Email
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.vkclient.R

sealed class NavigationItem(
    val titleResId: Int,
    val icon: ImageVector
) {
    object HomeItem :
        NavigationItem(
            titleResId = R.string.navigation_item_home,
            icon = Icons.Outlined.Home
        )

    object FavouriteItem : NavigationItem(
        titleResId = R.string.navigation_item_favourite,
        icon = Icons.Outlined.Favorite
    )

    object MessageItem : NavigationItem(
        titleResId = R.string.navigation_item_messages,
        icon = Icons.Outlined.Email
    )

    object ProfileItem : NavigationItem(
        titleResId = R.string.navigation_item_profile,
        icon = Icons.Outlined.Person
    )
}