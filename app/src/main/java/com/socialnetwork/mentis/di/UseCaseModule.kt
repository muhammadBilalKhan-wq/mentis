
package com.socialnetwork.mentis.di

import com.socialnetwork.mentis.domain.repository.FeedRepository
import com.socialnetwork.mentis.domain.usecase.GetFeedPostsUseCase
import com.socialnetwork.mentis.domain.usecase.GetFeedPostsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetFeedPostsUseCase(repository: FeedRepository): GetFeedPostsUseCase {
        return GetFeedPostsUseCaseImpl(repository)
    }
}