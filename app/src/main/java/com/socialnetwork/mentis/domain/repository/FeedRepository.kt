package com.socialnetwork.mentis.domain.repository

import androidx.paging.PagingData
import com.socialnetwork.mentis.domain.model.Post
import kotlinx.coroutines.flow.Flow

/**
 * Interface for the Feed repository.
 */
interface FeedRepository {

    /**
     * Returns a flow of paged posts.
     */
    fun getPosts(): Flow<PagingData<Post>>
}
