package com.socialnetwork.mentis.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.socialnetwork.mentis.core.domain.model.Post
import com.socialnetwork.mentis.data.dto.toPost
import retrofit2.HttpException
import java.io.IOException

class PostPagingSource(
    private val api: PostApi
) : PagingSource<Int, Post>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        val page = params.key ?: 1
        return try {
            val response = api.getPosts(page = page, limit = params.loadSize)
            val posts = response.map { it.toPost() }
            LoadResult.Page(
                data = posts,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (posts.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return state.anchorPosition
    }
}
