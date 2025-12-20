package com.socialnetwork.mentis.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostDto(
    val id: String,
    val imageUrl: String,
    val user: String,
    val likes: Int,
    val comments: Int,
    val caption: String
)
