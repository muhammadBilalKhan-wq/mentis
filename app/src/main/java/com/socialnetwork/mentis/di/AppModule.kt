package com.socialnetwork.mentis.di

import com.socialnetwork.mentis.data.local.AppDatabase
import com.socialnetwork.mentis.data.remote.FeedApi
import com.socialnetwork.mentis.data.repository.FeedRepositoryImpl
import com.socialnetwork.mentis.domain.repository.FeedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val TIMEOUT_MILLISECONDS = 15_000L

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.BODY
            }
            install(HttpTimeout) {
                connectTimeoutMillis = TIMEOUT_MILLISECONDS
                requestTimeoutMillis = TIMEOUT_MILLISECONDS
                socketTimeoutMillis = TIMEOUT_MILLISECONDS
            }
        }
    }

    @Provides
    @Singleton
    fun provideFeedApi(client: HttpClient): FeedApi {
        return FeedApi(client)
    }

    @Provides
    @Singleton
    fun provideFeedRepository(feedApi: FeedApi, appDatabase: AppDatabase): FeedRepository {
        return FeedRepositoryImpl(feedApi, appDatabase)
    }
}
