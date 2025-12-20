package com.socialnetwork.mentis.data.mapper

import com.socialnetwork.mentis.data.local.entity.PostEntity
import com.socialnetwork.mentis.domain.model.Post

fun PostEntity.toPost(): Post {
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

fun Post.toPostEntity(): PostEntity {
    return PostEntity(
        id = id,
        userId = "", 
        username = user,
        userImageUrl = "",
        imageUrl = imageUrl,
        description = caption,
        timestamp = timestamp,
        likeCount = likes,
        commentCount = comments
    )
}
