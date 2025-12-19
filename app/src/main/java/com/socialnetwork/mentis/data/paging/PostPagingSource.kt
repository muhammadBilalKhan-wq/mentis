package com.socialnetwork.mentis.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.socialnetwork.mentis.domain.model.Post
import kotlinx.coroutines.delay

class PostPagingSource : PagingSource<Int, Post>() {

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            val page = params.key ?: 1
            // Simulate network delay
            delay(1000)

            // Generate some fake posts
            val posts = (1..params.loadSize).map {
                val id = (page - 1) * params.loadSize + it
                Post(
                    id = "id_$id",
                    author = "Author $id",
                    content = "This is post number $id"
                )
            }

            LoadResult.Page(
                data = posts,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (posts.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}