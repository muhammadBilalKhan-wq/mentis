package com.socialnetwork.mentis.data.mapper

import com.socialnetwork.mentis.data.dto.PostDto
import com.socialnetwork.mentis.data.local.entity.PostEntity
import com.socialnetwork.mentis.domain.model.Post

fun PostDto.toEntity(): PostEntity {
    return PostEntity(
        id = id,
        description = description,
        date = date,
        likes = likes,
        comments = comments,
        imageUrl = imageUrl,
        user = user
    )
}

fun PostEntity.toDomain(): Post {
    return Post(
        id = id,
        description = description,
        date = date,
        likes = likes,
        comments = comments,
        imageUrl = imageUrl,
        user = user
    )
}
