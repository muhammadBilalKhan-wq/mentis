package com.socialnetwork.mentis.di

import com.socialnetwork.mentis.data.repository.FeedRepositoryImpl
import com.socialnetwork.mentis.domain.repository.FeedRepository
import com.socialnetwork.mentis.domain.usecase.GetFeedPostsUseCase
import com.socialnetwork.mentis.domain.usecase.GetFeedPostsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindGetFeedPostsUseCase(impl: GetFeedPostsUseCaseImpl): GetFeedPostsUseCase

    @Binds
    @Singleton
    abstract fun bindFeedRepository(impl: FeedRepositoryImpl): FeedRepository

}
