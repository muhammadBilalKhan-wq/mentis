package com.socialnetwork.mentis.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.socialnetwork.mentis.data.local.AppDatabase
import com.socialnetwork.mentis.data.local.entity.PostEntity
import com.socialnetwork.mentis.data.local.entity.RemoteKeyEntity
import com.socialnetwork.mentis.data.remote.FeedApi
import com.socialnetwork.mentis.data.remote.dto.PostDto

@OptIn(ExperimentalPagingApi::class)
class PostRemoteMediator(
    private val feedApi: FeedApi,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, PostEntity>() {

    private val postDao = appDatabase.postDao()
    private val remoteKeyDao = appDatabase.remoteKeyDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, PostEntity>): MediatorResult {
        return try {

            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = appDatabase.withTransaction {
                        remoteKeyDao.getRemoteKey("post")
                    }
                    if (remoteKey.nextPage == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    remoteKey.nextPage
                }
            }

            val response = feedApi.getFeed(page = page, pageSize = state.config.pageSize)

            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    postDao.deleteAllPosts()
                    remoteKeyDao.deleteRemoteKey("post")
                }
                remoteKeyDao.insertOrReplace(RemoteKeyEntity(label = "post", nextPage = page + 1))
                postDao.insertOrReplace(response.map { it.toPostEntity() })
            }

            MediatorResult.Success(endOfPaginationReached = response.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
    private fun PostDto.toPostEntity() = PostEntity(
        id = id,
        description = description,
        image = image,
        likes = likes,
        comments = comments,
        shares = shares,
        author = author
    )

}
