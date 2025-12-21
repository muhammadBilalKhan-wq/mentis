package com.socialnetwork.mentis.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.socialnetwork.mentis.core.data.local.AppDatabase
import com.socialnetwork.mentis.core.data.paging.PostRemoteMediator
import com.socialnetwork.mentis.core.data.remote.FeedApi
import com.socialnetwork.mentis.core.domain.model.Post
import com.socialnetwork.mentis.core.domain.repository.FeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class FeedRepositoryImpl @Inject constructor(
    private val feedApi: FeedApi,
    private val appDatabase: AppDatabase
) : FeedRepository {
    override fun getFeedPosts(): Flow<PagingData<Post>> {
        val pagingSourceFactory = { appDatabase.postDao().getPosts() }
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = PostRemoteMediator(
                feedApi = feedApi,
                appDatabase = appDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map { it.toPost() }
        }
    }
}
