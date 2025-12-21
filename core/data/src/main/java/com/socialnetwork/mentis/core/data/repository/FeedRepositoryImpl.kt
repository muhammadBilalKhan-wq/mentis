package com.socialnetwork.mentis.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.socialnetwork.mentis.core.data.mapper.toDomain
import com.socialnetwork.mentis.core.data.remote.FeedApi
import com.socialnetwork.mentis.core.domain.model.Post
import com.socialnetwork.mentis.core.domain.repository.FeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class FeedRepositoryImpl @Inject constructor(
    private val feedApi: FeedApi
) : FeedRepository {

    override fun getFeedPosts(): Flow<PagingData<Post>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { FeedPagingSource(feedApi) }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }
}
