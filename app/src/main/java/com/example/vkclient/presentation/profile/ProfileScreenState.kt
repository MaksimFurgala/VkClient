package com.example.vkclient.presentation.profile

import com.example.vkclient.domain.entity.Profile

sealed class ProfileScreenState {

    object Initial : ProfileScreenState()

    object Loading : ProfileScreenState()

    data class ProfileState(
        val profile: Profile
    ) :
        ProfileScreenState()
}