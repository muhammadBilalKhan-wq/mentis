package com.socialnetwork.mentis.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.socialnetwork.mentis.domain.model.Post

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey val id: String,
    val description: String,
    val image: String? = null,
    val likes: Int = 0,
    val comments: Int = 0,
    val shares: Int = 0,
    val author: String,
    val timestamp: Long
){
    fun toPost(): Post {
        return Post(
            id = id,
            user = author,
            imageUrl = image ?: "",
            caption = description,
            likes = likes,
            comments = comments,
            timestamp = timestamp
        )
    }
}
