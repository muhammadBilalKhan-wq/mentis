package com.socialnetwork.mentis.data.remote.dto

import com.socialnetwork.mentis.domain.model.Post

data class PostDto(
    val id: String,
    val user: String,
    val imageUrl: String,
    val caption: String,
    val likes: Int,
    val comments: Int,
    val timestamp: Long
) {
    fun toPost(): Post {
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
