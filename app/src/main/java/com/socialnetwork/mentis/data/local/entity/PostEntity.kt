package com.socialnetwork.mentis.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "posts",
    indices = [Index(value = ["id"], unique = true)]
)
data class PostEntity(
    @PrimaryKey
    val id: String,
    val userId: String,
    val username: String,
    val userImageUrl: String,
    val imageUrl: String,
    val description: String,
    val timestamp: Long,
    val likeCount: Int,
    val commentCount: Int
)
