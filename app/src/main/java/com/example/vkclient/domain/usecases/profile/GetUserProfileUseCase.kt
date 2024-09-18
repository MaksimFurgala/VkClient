package com.example.vkclient.domain.usecases.profile

import com.example.vkclient.domain.entity.Profile
import com.example.vkclient.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(val repository: ProfileRepository) {
    operator fun invoke(): Flow<Profile> {
        return repository.getProfileState()
    }
}