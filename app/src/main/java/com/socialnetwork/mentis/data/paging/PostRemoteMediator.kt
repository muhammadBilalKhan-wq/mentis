package com.socialnetwork.mentis.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.socialnetwork.mentis.core.data.remote.FeedApi
import com.socialnetwork.mentis.core.data.remote.dto.PostDto
import com.socialnetwork.mentis.data.local.AppDatabase
import com.socialnetwork.mentis.data.local.entity.PostEntity
import com.socialnetwork.mentis.data.local.entity.RemoteKeys

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

            val response = feedApi.getFeedPosts(page = page, limit = 10)

            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    postDao.clearAll()
                    remoteKeysDao.deleteRemoteKey("post")
                }
                remoteKeysDao.insertOrReplace(RemoteKeys(label = "post", nextPage = page + 1))
                postDao.insertAll(response.map { it.toEntity() })
            }

            MediatorResult.Success(endOfPaginationReached = response.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private fun PostDto.toEntity(): PostEntity {
        return PostEntity(
            id = this.id,
            description = this.caption,
            image = this.imageUrl,
            likes = this.likes,
            comments = this.comments,
            shares = 0, // No shares field in PostDto from core/data
            author = this.user,
            timestamp = System.currentTimeMillis()
        )
    }
}
