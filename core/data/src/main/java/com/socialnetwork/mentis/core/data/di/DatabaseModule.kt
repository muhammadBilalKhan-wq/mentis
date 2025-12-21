package com.socialnetwork.mentis.core.data.di

import android.app.Application
import androidx.room.Room
import com.socialnetwork.mentis.core.data.local.AppDatabase
import com.socialnetwork.mentis.core.data.local.dao.PostDao
import com.socialnetwork.mentis.core.data.local.dao.RemoteKeysDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "mentis_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providePostDao(db: AppDatabase): PostDao {
        return db.postDao()
    }

    @Provides
    @Singleton
    fun provideRemoteKeysDao(db: AppDatabase): RemoteKeysDao {
        return db.remoteKeysDao()
    }
}
