package com.socialnetwork.mentis.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.socialnetwork.mentis.domain.model.Post

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey
    val id: String,
    val user: String,
    val imageUrl: String,
    val caption: String,
    val likes: Int,
    val comments: Int,
    val timestamp: Long
) {
    fun toDomain(): Post {
        return Post(
            id = id,
            user = user,
            imageUrl = imageUrl,
            caption = caption,
            likes = likes,
            comments = comments,
            timestamp = timestamp
        )
    }
}
