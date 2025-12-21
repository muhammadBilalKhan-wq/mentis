package com.socialnetwork.mentis.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.socialnetwork.mentis.data.local.AppDatabase
import com.socialnetwork.mentis.data.local.entity.PostEntity
import com.socialnetwork.mentis.data.local.entity.RemoteKeys
import com.socialnetwork.mentis.data.remote.FeedApi

@OptIn(ExperimentalPagingApi::class)
class PostRemoteMediator(
    private val feedApi: FeedApi,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, PostEntity>() {

    private val postDao = appDatabase.postDao()
    private val remoteKeysDao = appDatabase.remoteKeysDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, PostEntity>): MediatorResult {
        return try {

            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = appDatabase.withTransaction {
                        remoteKeysDao.getRemoteKey("post")
                    }
                    if (remoteKey.nextPage == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    remoteKey.nextPage
                }
            }

            val response = feedApi.getPosts(page = page, limit = 10)

            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    postDao.clearAll()
                    remoteKeysDao.deleteRemoteKey("post")
                }
                remoteKeysDao.insertOrReplace(RemoteKeys(label = "post", nextPage = page + 1))
                postDao.insertAll(response.map { it.toPostEntity() })
            }

            MediatorResult.Success(endOfPaginationReached = response.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
