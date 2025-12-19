package com.socialnetwork.mentis.data.repository

import com.socialnetwork.mentis.domain.model.Post
import com.socialnetwork.mentis.domain.repository.FeedRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Fake implementation of [FeedRepository] for testing and local development.
 */
class FakeFeedRepository @Inject constructor() : FeedRepository {

    /**
     * Emits a predefined list of posts after a short delay to simulate a network call.
     * The flow will emit a success result with the list of posts.
     */
    override fun getPosts(): Flow<Result<List<Post>>> = flow {
        // Simulate network delay
        delay(1500)

        val fakePosts = listOf(
            Post(id = "fake-1", author = "Senior Dev", content = "This is a fake post from the FakeFeedRepository!"),
            Post(id = "fake-2", author = "Hilt", content = "Dependency injection is working correctly."),
            Post(id = "fake-3", author = "Kotlin Flows", content = "Emitting data asynchronously.")
        )
        // Emit the success result with the fake data
        emit(Result.success(fakePosts))
    }
}
