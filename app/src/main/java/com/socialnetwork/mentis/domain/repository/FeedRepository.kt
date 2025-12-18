package com.socialnetwork.mentis.domain.repository

import com.socialnetwork.mentis.domain.model.Post
import kotlinx.coroutines.flow.Flow

/**
 * Interface for the Feed repository.
 */
interface FeedRepository {

    /**
     * Returns a flow of posts.
     */
    fun getPosts(): Flow<Result<List<Post>>>
}
