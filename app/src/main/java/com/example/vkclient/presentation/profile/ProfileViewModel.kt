package com.example.vkclient.presentation.profile

import androidx.lifecycle.ViewModel
import com.example.vkclient.domain.usecases.profile.GetUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase,
) : ViewModel() {

    private val profileFlow = getUserProfileUseCase()

    // State для профиля.
    val screenState = profileFlow
        .map {
            ProfileScreenState.ProfileState(
                profile = it
            ) as ProfileScreenState
        }
        .onStart { emit(ProfileScreenState.Loading) }
}