package com.socialnetwork.mentis.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.socialnetwork.mentis.core.data.local.dao.PostDao
import com.socialnetwork.mentis.core.data.local.dao.RemoteKeysDao
import com.socialnetwork.mentis.core.data.local.entity.PostEntity
import com.socialnetwork.mentis.core.data.local.entity.RemoteKeys

@Database(
    entities = [PostEntity::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}
