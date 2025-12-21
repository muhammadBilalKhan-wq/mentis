package com.socialnetwork.mentis.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.socialnetwork.mentis.data.local.AppDatabase
import com.socialnetwork.mentis.data.local.entity.PostEntity
import com.socialnetwork.mentis.data.paging.PostRemoteMediator
import com.socialnetwork.mentis.data.remote.FeedApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PostRepository @Inject constructor(
    private val feedApi: FeedApi,
    private val appDatabase: AppDatabase
) {
    fun getPosts(): Flow<PagingData<PostEntity>> {
        val pagingSourceFactory = { appDatabase.postDao().getPosts() }
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = PostRemoteMediator(
                feedApi = feedApi,
                appDatabase = appDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}
