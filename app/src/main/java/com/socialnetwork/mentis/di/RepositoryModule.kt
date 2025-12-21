package com.socialnetwork.mentis.di

import com.socialnetwork.mentis.data.repository.PostRepository
import com.socialnetwork.mentis.data.repository.PostRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindPostRepository(impl: PostRepositoryImpl): PostRepository
}
