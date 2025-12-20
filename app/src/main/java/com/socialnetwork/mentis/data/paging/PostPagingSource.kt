package com.socialnetwork.mentis.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.socialnetwork.mentis.core.data.mapper.toDomain
import com.socialnetwork.mentis.core.data.remote.FeedApi
import com.socialnetwork.mentis.core.domain.model.Post
import com.socialnetwork.mentis.core.domain.model.Result

class PostPagingSource(
    private val feedApi: FeedApi
) : PagingSource<Int, Post>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        val page = params.key ?: 1
        val pageSize = params.loadSize

        return when (val result = feedApi.getFeed(page, pageSize)) {
            is Result.Success -> {
                val posts = result.data.map { it.toDomain() }
                LoadResult.Page(
                    data = posts,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (posts.isEmpty()) null else page + 1
                )
            }
            is Result.Error -> {
                LoadResult.Error(Exception(result.error.message))
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
