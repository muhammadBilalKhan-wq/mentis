package com.socialnetwork.mentis.domain.repository

import androidx.paging.PagingData
import com.socialnetwork.mentis.domain.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeFeedRepository : FeedRepository {

    override fun getFeedPosts(): Flow<PagingData<Post>> {
        return flowOf(PagingData.from(emptyList()))
    }
}
