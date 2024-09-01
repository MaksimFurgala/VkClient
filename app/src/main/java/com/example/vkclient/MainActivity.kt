package com.example.vkclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.vkclient.ui.theme.MainScreen
import com.example.vkclient.ui.theme.VkClientTheme
import com.vk.api.sdk.auth.VKScope
import com.vk.id.VKID
import com.vk.id.auth.VKIDAuthUiParams
import com.vk.id.onetap.compose.onetap.OneTap

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            VkClientTheme(dynamicColor = false) {
                VKID.init(this)
                ScreenWithVkIdButton()
            }
        }
    }

    @Composable
    fun ScreenWithVkIdButton() {
        val authState = remember {
            mutableStateOf(false)
        }

        if (authState.value) {
            MainScreen()
        } else {
            OneTap(authParams = VKIDAuthUiParams.Builder().apply {
                scopes = emptySet()
            }.build(),
                onAuth = { oAuth, accessToken ->
                    authState.value = true
                    // TODO: сформировать сохранение token'а
                })
        }
    }
}