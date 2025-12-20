package com.socialnetwork.mentis.data.mapper

import com.socialnetwork.mentis.data.remote.dto.PostDto
import com.socialnetwork.mentis.domain.model.Post

fun PostDto.toDomain(): Post {
    return Post(
        id = id,
        imageUrl = imageUrl,
        user = user,
        likes = likes,
        comments = comments,
        caption = caption
    )
}
