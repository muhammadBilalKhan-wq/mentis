package com.socialnetwork.mentis.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.socialnetwork.mentis.core.domain.model.Post
import com.socialnetwork.mentis.core.domain.repository.FeedRepository
import com.socialnetwork.mentis.data.paging.FeedPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor() : FeedRepository {
    override fun getFeedPosts(): Flow<PagingData<Post>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { FeedPagingSource() }
        ).flow
    }
}
