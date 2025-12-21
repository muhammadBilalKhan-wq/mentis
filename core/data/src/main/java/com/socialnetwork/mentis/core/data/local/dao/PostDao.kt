package com.socialnetwork.mentis.core.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.socialnetwork.mentis.core.data.local.entity.PostEntity

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<PostEntity>)

    @Query("SELECT * FROM posts ORDER BY timestamp DESC")
    fun getPosts(): PagingSource<Int, PostEntity>

    @Query("DELETE FROM posts")
    suspend fun clearAll()
}
