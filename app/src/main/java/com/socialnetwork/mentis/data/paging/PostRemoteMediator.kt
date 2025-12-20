package com.socialnetwork.mentis.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.socialnetwork.mentis.data.local.AppDatabase
import com.socialnetwork.mentis.data.local.entity.PostEntity
import com.socialnetwork.mentis.data.local.entity.RemoteKeys
import com.socialnetwork.mentis.data.mapper.toEntity
import com.socialnetwork.mentis.data.remote.FeedApi
import io.ktor.client.plugins.ResponseException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PostRemoteMediator(
    private val feedApi: FeedApi,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, PostEntity>() {

    private val postDao = appDatabase.postDao()
    private val remoteKeysDao = appDatabase.remoteKeysDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, PostEntity>): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevKey
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextKey
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = feedApi.getPosts()
            val endOfPaginationReached = response.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    postDao.clearPosts()
                    remoteKeysDao.clearRemoteKeys()
                }
                val keys = response.map {
                    RemoteKeys(
                        repoId = it.id,
                        prevKey = prevPage,
                        nextKey = nextPage
                    )
                }
                remoteKeysDao.insertAll(keys)
                postDao.insertAll(response.map { it.toEntity() })
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: ResponseException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, PostEntity>): RemoteKeys? {
        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.id?.let {
                remoteKeysDao.remoteKeysRepoId(it)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, PostEntity>): RemoteKeys? {
        return state.pages.firstOrNull() { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { remoteKeysDao.remoteKeysRepoId(it.id) }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, PostEntity>): RemoteKeys? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { remoteKeysDao.remoteKeysRepoId(it.id) }
    }

}
