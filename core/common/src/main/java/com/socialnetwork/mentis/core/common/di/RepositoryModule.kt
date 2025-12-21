package com.socialnetwork.mentis.core.common.di

import com.socialnetwork.mentis.core.data.repository.FeedRepositoryImpl
import com.socialnetwork.mentis.core.domain.repository.FeedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindFeedRepository(impl: FeedRepositoryImpl): FeedRepository
}
