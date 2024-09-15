package com.example.vkclient.presentation.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.vkclient.navigation.AppNavGraph
import com.example.vkclient.navigation.rememberNavigationState
import com.example.vkclient.presentation.comments.CommentsScreen
import com.example.vkclient.presentation.feeds.FeedsScreen
import com.example.vkclient.presentation.messenger.MessengerScreen

/**
 * Главный экран приложения.
 *
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Preview
@Composable
fun MainScreen() {
    // Основной state навигации
    val navigationState = rememberNavigationState()

    Scaffold(
        bottomBar = {
            NavigationBar {
                // State для объекта бекстек.
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()

                // Элементы навигации.
                val itemsNavigation = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favourite,
                    NavigationItem.Message,
                    NavigationItem.Profile
                )

                //region Создание панели навигации на основании переданных элементов навигации.
                itemsNavigation.forEach { navigationItem ->

                    val selected = navBackStackEntry?.destination?.hierarchy?.any {
                        it.route == navigationItem.screen.route
                    } ?: false

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navigationState.navigateTo(navigationItem.screen.route)
                        },
                        icon = {
                            Icon(imageVector = navigationItem.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = stringResource(id = navigationItem.titleResId))
                        },
                        colors = NavigationBarItemDefaults.colors().copy(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSecondary
                        )
                    )
                }
                //endregion
            }
        }) { paddingValues ->

        //region Навигационный граф.
        AppNavGraph(
            navHostController = navigationState.navHostController,
            feedScreenContent = {
                FeedsScreen(
                    paddingValues = paddingValues,
                    onCommentClickListener = {
                        navigationState.navigateToComments(it)
                    }
                )
            },
            commentsScreenContent = { feedPost ->
                CommentsScreen(feedPost = feedPost, onBackPressed = {
                    navigationState.navHostController.popBackStack()
                })
            },
            // TODO: доработать экраны.
            favouriteScreenContent = { Text(text = "FavouriteItem", color = Color.Black) },
            messengerScreenContent = {
                MessengerScreen(onBackPressed = {
                    navigationState.navHostController.popBackStack()
                })
            },
            profileScreenContent = { Text(text = "ProfileItem", color = Color.Black) })
        //endregion
    }
}