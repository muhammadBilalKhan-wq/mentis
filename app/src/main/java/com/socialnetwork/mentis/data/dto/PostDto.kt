package com.socialnetwork.mentis.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostDto(
    val id: String,
    val user: String,
    val imageUrl: String,
    val caption: String,
    val likes: Int,
    val comments: Int,
    val timestamp: Long
)
