package com.socialnetwork.mentis.data.repository

import com.socialnetwork.mentis.domain.model.Post
import com.socialnetwork.mentis.domain.repository.FeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FeedRepositoryImpl : FeedRepository {
    override fun getPosts(): Flow<Result<List<Post>>> {
        // In a real app, you'd fetch this from a data source.
        val posts = listOf(
            Post(id = "1", author = "John Doe", content = "This is a great post!"),
            Post(id = "2", author = "Jane Smith", content = "I agree, this is awesome."),
            Post(id = "3", author = "Sam Wilson", content = "Enjoying this discussion.")
        )
        return flowOf(Result.success(posts))
    }
}
