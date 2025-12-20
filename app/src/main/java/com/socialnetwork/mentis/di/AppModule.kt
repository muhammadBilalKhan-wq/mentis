package com.socialnetwork.mentis.di

import android.content.Context
import androidx.room.Room
import com.socialnetwork.mentis.data.local.AppDatabase
import com.socialnetwork.mentis.data.remote.FeedApi
import com.socialnetwork.mentis.data.repository.FeedRepositoryImpl
import com.socialnetwork.mentis.domain.repository.FeedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
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

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "mentis-db"
        ).build()
    }

    @Provides
    @Singleton
    fun providePostDao(appDatabase: AppDatabase) = appDatabase.postDao()

    @Provides
    @Singleton
    fun provideRemoteKeysDao(appDatabase: AppDatabase) = appDatabase.remoteKeysDao()
}
