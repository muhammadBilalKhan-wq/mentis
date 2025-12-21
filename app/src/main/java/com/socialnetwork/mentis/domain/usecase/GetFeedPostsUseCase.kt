package com.socialnetwork.mentis.domain.usecase

import androidx.paging.PagingData
import com.socialnetwork.mentis.core.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface GetFeedPostsUseCase {
    operator fun invoke(): Flow<PagingData<Post>>
}
