package com.socialnetwork.mentis.domain.repository

import androidx.paging.PagingData
import com.socialnetwork.mentis.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface FeedRepository {
    fun getFeedPosts(): Flow<PagingData<Post>>
}
