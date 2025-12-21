package com.socialnetwork.mentis.data.remote.dto

import com.socialnetwork.mentis.data.local.entity.PostEntity

data class PostDto(
    val id: String,
    val description: String,
    val image: String? = null,
    val likes: Int = 0,
    val comments: Int = 0,
    val shares: Int = 0,
    val author: String
){
    fun toPostEntity(): PostEntity {
        return PostEntity(
            id = id,
            description = description,
            image = image,
            likes = likes,
            comments = comments,
            shares = shares,
            author = author,
            timestamp = System.currentTimeMillis()
        )
    }
}
