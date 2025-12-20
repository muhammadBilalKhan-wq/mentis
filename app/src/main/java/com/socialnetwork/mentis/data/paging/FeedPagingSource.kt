package com.socialnetwork.mentis.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.socialnetwork.mentis.data.mapper.toDomain
import com.socialnetwork.mentis.data.remote.FeedApi
import com.socialnetwork.mentis.domain.model.Post

class FeedPagingSource(private val feedApi: FeedApi) : PagingSource<Int, Post>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            val posts = feedApi.getPosts().map { it.toDomain() }
            LoadResult.Page(
                data = posts,
                prevKey = null,
                nextKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return null
    }
}
