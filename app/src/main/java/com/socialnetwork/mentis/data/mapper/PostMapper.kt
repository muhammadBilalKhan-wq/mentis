package com.socialnetwork.mentis.data.mapper

import com.socialnetwork.mentis.data.local.entity.PostEntity
import com.socialnetwork.mentis.domain.model.Post

fun PostEntity.toPost(): Post {
    return Post(
        id = id,
        userId = userId,
        username = username,
        userImageUrl = userImageUrl,
        imageUrl = imageUrl,
        description = description,
        timestamp = timestamp,
        likeCount = likeCount,
        commentCount = commentCount
    )
}

fun Post.toPostEntity(): PostEntity {
    return PostEntity(
        id = id,
        userId = userId,
        username = username,
        userImageUrl = userImageUrl,
        imageUrl = imageUrl,
        description = description,
        timestamp = timestamp,
        likeCount = likeCount,
        commentCount = commentCount
    )
}
