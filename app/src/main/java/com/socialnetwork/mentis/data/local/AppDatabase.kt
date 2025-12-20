package com.socialnetwork.mentis.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.socialnetwork.mentis.data.local.dao.PostDao
import com.socialnetwork.mentis.data.local.entity.PostEntity

@Database(
    entities = [PostEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}
