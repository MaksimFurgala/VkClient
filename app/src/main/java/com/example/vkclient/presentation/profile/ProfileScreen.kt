package com.example.vkclient.presentation.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.vkclient.domain.entity.Profile
import com.example.vkclient.ui.theme.darkBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(onBackPressed: () -> Unit) {
    val viewModel: ProfileViewModel = hiltViewModel()
    val screenState = viewModel.screenState.collectAsState(ProfileScreenState.Initial)

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Профиль") },
            navigationIcon = {
                IconButton(onClick = { onBackPressed() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = null
                    )
                }
            })
    }) { paddingValues ->
        when (val currentState = screenState.value) {
            is ProfileScreenState.ProfileState -> {
                ProfileContent(
                    paddingValues = paddingValues,
                    profile = currentState.profile
                )
            }

            ProfileScreenState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = darkBlue)
                }
            }

            ProfileScreenState.Initial -> {

            }
        }
    }
}

@Composable
fun ProfileContent(
    paddingValues: PaddingValues,
    profile: Profile,
) {
    Column(modifier = Modifier.padding(paddingValues)) {
        // Аватарка.
        AsyncImage(
            modifier = Modifier.clip(CircleShape),
            model = profile.avatarUrl,
            contentDescription = null
        )
        // Id.
        Row {
            Icon(imageVector = Icons.Outlined.Info, contentDescription = null)
            Text(text = "id${profile.id.toString()}")
        }
        // Имя.
        Row {
            Icon(imageVector = Icons.Outlined.Person, contentDescription = null)
            Text(text = "Имя: ${profile.lastName} ${profile.firstName}")
        }
        // ДР
        Row {
            Icon(imageVector = Icons.Outlined.DateRange, contentDescription = null)
            Text(
                text = "День рождения: ${profile.birthday}"
            )
        }
    }
}