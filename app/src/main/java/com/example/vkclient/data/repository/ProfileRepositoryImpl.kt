package com.example.vkclient.data.repository

import com.example.vkclient.data.mapper.ProfileMapper
import com.example.vkclient.data.network.VkApiFactory
import com.example.vkclient.domain.entity.Profile
import com.example.vkclient.domain.repository.ProfileRepository
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val mapper: ProfileMapper,
    private val storage: VKPreferencesKeyValueStorage,
) : ProfileRepository {

    private val token
        get() = VKAccessToken.restore(storage)
    private val vkApiService = VkApiFactory.vkApiService
    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    private val _profileFlow = flow {
        val responseProfile = vkApiService.getUserProfile(getAccessToken())
        val profile = responseProfile.profile
        if (profile.isNotEmpty()) {
            emit(mapper.mapResponseToProfile(profile.first()))
        }
    }.flowOn(Dispatchers.IO)

    private val profile: Flow<Profile> = _profileFlow

    private fun getAccessToken(): String {
        return token?.accessToken ?: throw IllegalStateException("Access token is null")
    }

    override fun getProfileState(): Flow<Profile> = _profileFlow
}