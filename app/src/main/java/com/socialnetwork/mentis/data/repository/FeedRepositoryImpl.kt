package com.socialnetwork.mentis.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.socialnetwork.mentis.data.paging.PostPagingSource
import com.socialnetwork.mentis.data.remote.FeedApi
import com.socialnetwork.mentis.domain.model.Post
import com.socialnetwork.mentis.domain.repository.FeedRepository
import kotlinx.coroutines.flow.Flow

class FeedRepositoryImpl(private val feedApi: FeedApi) : FeedRepository {

    override fun getPosts(): Flow<PagingData<Post>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                PostPagingSource(feedApi)
            }
        ).flow
    }
}
