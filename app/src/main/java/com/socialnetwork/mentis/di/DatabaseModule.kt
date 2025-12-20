package com.socialnetwork.mentis.di

import android.content.Context
import androidx.room.Room
import com.socialnetwork.mentis.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

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
