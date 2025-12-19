package com.socialnetwork.mentis.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.socialnetwork.mentis.data.paging.PostPagingSource
import com.socialnetwork.mentis.domain.model.Post
import com.socialnetwork.mentis.domain.repository.FeedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FakeFeedRepository @Inject constructor() : FeedRepository {

    override fun getPosts(): Flow<PagingData<Post>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PostPagingSource() }
        ).flow
    }
}
