package com.socialnetwork.mentis.data.mapper

import com.socialnetwork.mentis.data.dto.PostDto
import com.socialnetwork.mentis.data.local.entity.PostEntity
import com.socialnetwork.mentis.domain.model.Post

fun PostDto.toEntity(): PostEntity {
    return PostEntity(
        id = id,
        user = user,
        imageUrl = imageUrl,
        caption = caption,
        likes = likes,
        comments = comments,
        timestamp = timestamp
    )
}

fun PostEntity.toDomain(): Post {
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
