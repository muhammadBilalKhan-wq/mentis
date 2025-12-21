package com.socialnetwork.mentis.core.domain.model

data class Post(
    val id: String,
    val author: String,
    val text: String,
    val imageUrl: String? = null,
    val timestamp: Long
)
