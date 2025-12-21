package com.socialnetwork.mentis.di

import com.socialnetwork.mentis.data.remote.PostApi
import com.socialnetwork.mentis.data.repository.PostPagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePostApi(): PostApi {
        return Retrofit.Builder()
            .baseUrl("https://mentis.social/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostApi::class.java)
    }

    @Provides
    fun providePostPagingSource(postApi: PostApi): PostPagingSource {
        return PostPagingSource(postApi)
    }
}
