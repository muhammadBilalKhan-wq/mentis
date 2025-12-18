package com.socialnetwork.mentis.domain.usecase

import com.socialnetwork.mentis.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface GetFeedPostsUseCase {
    operator fun invoke(): Flow<Result<List<Post>>>
}
