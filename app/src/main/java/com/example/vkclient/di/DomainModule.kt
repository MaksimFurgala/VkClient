package com.example.vkclient.di

import com.example.vkclient.domain.repository.FeedPostRepository
import com.example.vkclient.domain.usecases.auth.CheckAuthStateUseCase
import com.example.vkclient.domain.usecases.auth.GetAuthStateUseCase
import com.example.vkclient.domain.usecases.feedpost.ChangeLikeStatusUseCase
import com.example.vkclient.domain.usecases.feedpost.GetCommentsUseCase
import com.example.vkclient.domain.usecases.feedpost.GetRecommendationsUseCase
import com.example.vkclient.domain.usecases.feedpost.IgnoreFeedPostUseCase
import com.example.vkclient.domain.usecases.feedpost.LoadNextFeedPostUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideCheckAuthStateUseCase(repository: FeedPostRepository): CheckAuthStateUseCase {
        return CheckAuthStateUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAuthStateUseCase(repository: FeedPostRepository): GetAuthStateUseCase {
        return GetAuthStateUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideChangeLikeStatusUseCase(repository: FeedPostRepository): ChangeLikeStatusUseCase {
        return ChangeLikeStatusUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetCommentsUseCase(repository: FeedPostRepository): GetCommentsUseCase {
        return GetCommentsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetRecommendationsUseCase(repository: FeedPostRepository): GetRecommendationsUseCase {
        return GetRecommendationsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideIgnoreFeedPostUseCase(repository: FeedPostRepository): IgnoreFeedPostUseCase {
        return IgnoreFeedPostUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideLoadNextFeedPostUseCase(repository: FeedPostRepository): LoadNextFeedPostUseCase {
        return LoadNextFeedPostUseCase(repository)
    }
}