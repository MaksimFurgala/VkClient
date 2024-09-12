package com.example.vkclient.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.vkclient.domain.entity.AuthState
import com.example.vkclient.ui.theme.VkClientTheme
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            VkClientTheme(dynamicColor = false) {
                VK.initialize(this)
                ScreenWithVkIdButton()
            }
        }
    }

    /**
     * Экран с выводом окна авторизации/главный экран приложения.
     *
     */
    @Composable
    fun ScreenWithVkIdButton() {
        val mainViewModel: MainViewModel = hiltViewModel()
        val authState = mainViewModel.authState.collectAsState(AuthState.Initial)

        val launcher =
            rememberLauncherForActivityResult(contract = VK.getVKAuthActivityResultContract()) {
                mainViewModel.performAuthResult()
            }

        when (authState.value) {
            AuthState.Authorized -> {
                MainScreen()
            }

            AuthState.NotAuthorized -> {
                LoginScreen {
                    launcher.launch(
                        listOf(
                            VKScope.WALL,
                            VKScope.FRIENDS,
                            VKScope.EMAIL,
                            VKScope.GROUPS
                        )
                    )
                }
            }

            else -> {

            }
        }
    }
}