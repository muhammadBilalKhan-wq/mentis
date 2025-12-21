package com.socialnetwork.mentis.core.domain.repository

import androidx.paging.PagingData
import com.socialnetwork.mentis.core.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface FeedRepository {
    fun getFeedPosts(): Flow<PagingData<Post>>
}
