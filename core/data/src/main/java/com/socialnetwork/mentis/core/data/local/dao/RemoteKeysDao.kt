package com.socialnetwork.mentis.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.socialnetwork.mentis.core.data.local.entity.RemoteKeys

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: RemoteKeys)

    @Query("SELECT * FROM remote_keys WHERE label = :query")
    suspend fun getRemoteKey(query: String): RemoteKeys

    @Query("DELETE FROM remote_keys WHERE label = :query")
    suspend fun deleteRemoteKey(query: String)
}
