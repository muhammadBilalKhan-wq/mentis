package com.socialnetwork.mentis.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.socialnetwork.mentis.data.mapper.toDomain
import com.socialnetwork.mentis.data.remote.FeedApi
import com.socialnetwork.mentis.domain.model.Post

class PostPagingSource(
    private val feedApi: FeedApi
) : PagingSource<Int, Post>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        val page = params.key ?: 1
        val pageSize = params.loadSize

        return try {
            val posts = feedApi.getPosts().map { it.toDomain() }
            LoadResult.Page(
                data = posts,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (posts.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
