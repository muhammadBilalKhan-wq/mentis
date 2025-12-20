package com.socialnetwork.mentis.data.mapper

import com.socialnetwork.mentis.data.dto.PostDto
import com.socialnetwork.mentis.data.local.entity.PostEntity
import com.socialnetwork.mentis.domain.model.Post

fun PostDto.toEntity(): PostEntity {
    return PostEntity(
        id = id,
        userId = "", // Placeholder, not available in PostDto
        username = user,
        userImageUrl = "", // Placeholder, not available in PostDto
        imageUrl = imageUrl,
        description = caption,
        timestamp = timestamp,
        likeCount = likes,
        commentCount = comments
    )
}

fun PostEntity.toDomain(): Post {
    return Post(
        id = id,
        imageUrl = imageUrl,
        user = username,
        likes = likeCount,
        comments = commentCount,
        caption = description,
        timestamp = timestamp
    )
}
