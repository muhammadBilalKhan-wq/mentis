package com.socialnetwork.mentis.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey val id: String,
    val description: String,
    val image: String? = null,
    val likes: Int = 0,
    val comments: Int = 0,
    val author: String,
    val timestamp: Long
)
