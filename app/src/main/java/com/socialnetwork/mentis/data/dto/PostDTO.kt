package com.socialnetwork.mentis.data.dto

import com.socialnetwork.mentis.core.domain.model.Post

data class PostDTO(
    val id: String,
    val author: String,
    val text: String,
    val imageUrl: String? = null,
    val timestamp: Long
) {
    fun toPost(): Post {
        return Post(
            id = id,
            author = author,
            text = text,
            imageUrl = imageUrl,
            timestamp = timestamp
        )
    }
}
