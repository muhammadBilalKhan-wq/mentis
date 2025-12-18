package com.socialnetwork.mentis.domain.model

/**
 * Represents a single post in the feed.
 *
 * @param id the unique identifier of the post.
 * @param author the author of the post.
 * @param content the content of the post.
 */
data class Post(
    val id: String,
    val author: String,
    val content: String
)
