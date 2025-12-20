package com.socialnetwork.mentis.core.data.mapper

import com.socialnetwork.mentis.core.data.remote.dto.PostDto
import com.socialnetwork.mentis.core.domain.model.Post

fun PostDto.toDomain(): Post {
    return Post(
        id = this.id,
        imageUrl = this.imageUrl,
        user = this.user,
        likes = this.likes,
        comments = this.comments,
        caption = this.caption
    )
}
