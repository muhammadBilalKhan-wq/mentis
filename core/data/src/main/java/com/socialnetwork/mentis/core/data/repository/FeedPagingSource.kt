package com.socialnetwork.mentis.core.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.socialnetwork.mentis.core.data.remote.FeedApi
import com.socialnetwork.mentis.core.data.remote.dto.PostDto

class FeedPagingSource(
    private val feedApi: FeedApi
) : PagingSource<Int, PostDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostDto> {
        return try {
            val page = params.key ?: 1
            val response = feedApi.getFeedPosts(page = page, limit = params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PostDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
