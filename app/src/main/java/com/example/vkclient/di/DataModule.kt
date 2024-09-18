package com.example.vkclient.di

import android.content.Context
import com.example.vkclient.data.mapper.FeedPostMapper
import com.example.vkclient.data.mapper.ProfileMapper
import com.example.vkclient.data.repository.FeedPostRepositoryImpl
import com.example.vkclient.data.repository.ProfileRepositoryImpl
import com.example.vkclient.domain.repository.FeedPostRepository
import com.example.vkclient.domain.repository.ProfileRepository
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideFeedPostRepository(
        @ApplicationContext context: Context,
        storage: VKPreferencesKeyValueStorage,
        feedPostMapper: FeedPostMapper,
    ): FeedPostRepository {
        return FeedPostRepositoryImpl(context, storage, feedPostMapper)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(
        profileMapper: ProfileMapper,
        storage: VKPreferencesKeyValueStorage,
    ): ProfileRepository {
        return ProfileRepositoryImpl(profileMapper, storage)
    }

    @Provides
    @Singleton
    fun provideVKPreferencesStorage(@ApplicationContext context: Context): VKPreferencesKeyValueStorage {
        return VKPreferencesKeyValueStorage(context)
    }
}