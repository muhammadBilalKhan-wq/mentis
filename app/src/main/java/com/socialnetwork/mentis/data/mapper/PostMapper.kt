package com.socialnetwork.mentis.data.mapper

import com.socialnetwork.mentis.data.dto.PostDto
import com.socialnetwork.mentis.data.local.entity.PostEntity

fun PostDto.toEntity(): PostEntity {
    return PostEntity(
        id = id.toString(),
        user = "User $userId",
        imageUrl = "https://picsum.photos/id/$id/800/800",
        caption = title,
        likes = (10..1000).random(),
        comments = (5..200).random(),
        timestamp = System.currentTimeMillis() - (0..100000000).random()
    )
}
