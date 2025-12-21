package com.socialnetwork.mentis.core.common.di

import com.socialnetwork.mentis.core.data.remote.FeedApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json()
            }
        }
    }

    @Provides
    @Singleton
    fun provideFeedApi(client: HttpClient): FeedApi {
        return FeedApi(client)
    }

}
