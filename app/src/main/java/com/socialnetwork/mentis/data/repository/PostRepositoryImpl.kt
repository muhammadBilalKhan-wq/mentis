package com.socialnetwork.mentis.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.socialnetwork.mentis.core.domain.model.Post
import com.socialnetwork.mentis.core.domain.repository.PostRepository
import com.socialnetwork.mentis.data.remote.PostApi
import com.socialnetwork.mentis.data.remote.PostPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val api: PostApi
) : PostRepository {
    override fun getFeedPosts(): Flow<PagingData<Post>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { PostPagingSource(api) }
        ).flow
    }
}
