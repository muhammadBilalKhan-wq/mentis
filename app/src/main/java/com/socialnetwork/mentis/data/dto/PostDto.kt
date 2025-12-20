package com.socialnetwork.mentis.data.dto

data class PostDto(
    val id: String,
    val user: String,
    val imageUrl: String,
    val caption: String,
    val likes: Int,
    val comments: Int,
    val timestamp: Long
)
