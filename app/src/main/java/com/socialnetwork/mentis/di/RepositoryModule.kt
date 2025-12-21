package com.socialnetwork.mentis.di

import com.socialnetwork.mentis.data.repository.FeedRepositoryImpl
import com.socialnetwork.mentis.data.repository.PostRepository
import com.socialnetwork.mentis.data.repository.PostRepositoryImpl
import com.socialnetwork.mentis.domain.repository.FeedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindPostRepository(impl: PostRepositoryImpl): PostRepository

    @Binds
    abstract fun bindFeedRepository(impl: FeedRepositoryImpl): FeedRepository
}
