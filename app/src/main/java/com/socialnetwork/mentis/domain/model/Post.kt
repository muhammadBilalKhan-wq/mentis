package com.socialnetwork.mentis.domain.model

data class Post(
    val id: String,
    val imageUrl: String,
    val user: String,
    val likes: Int,
    val comments: Int,
    val caption: String,
    val timestamp: Long
)
