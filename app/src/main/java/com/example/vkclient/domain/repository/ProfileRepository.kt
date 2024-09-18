package com.example.vkclient.domain.repository

import com.example.vkclient.domain.entity.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getProfileState(): Flow<Profile>
}