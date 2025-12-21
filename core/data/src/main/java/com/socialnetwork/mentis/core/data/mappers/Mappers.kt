package com.socialnetwork.mentis.core.data.mappers

import com.socialnetwork.mentis.core.data.local.entity.PostEntity
import com.socialnetwork.mentis.core.data.remote.dto.PostDto
import com.socialnetwork.mentis.core.domain.model.Post

fun PostDto.toEntity(): PostEntity {
    return PostEntity(
        id = this.id,
        description = this.caption,
        image = this.imageUrl,
        likes = this.likes,
        comments = this.comments,
        author = this.user,
        timestamp = System.currentTimeMillis()
    )
}

fun PostEntity.toPost(): Post {
    return Post(
        id = this.id,
        description = this.description,
        image = this.image,
        likes = this.likes,
        comments = this.comments,
        author = this.author,
        timestamp = this.timestamp
    )
}
