package com.socialnetwork.mentis.data.repository

import androidx.paging.PagingData
import com.socialnetwork.mentis.domain.model.Post
import com.socialnetwork.mentis.domain.repository.FeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FakeFeedRepository @Inject constructor() : FeedRepository {

    override fun getPosts(): Flow<PagingData<Post>> {
        val fakePosts = listOf(
            Post(
                id = "1",
                user = "Test User 1",
                imageUrl = "",
                caption = "Hello World!",
                likes = 100,
                comments = 12,
                timestamp = 0
            ),
            Post(
                id = "2",
                user = "Test User 2",
                imageUrl = "",
                caption = "This is a test post.",
                likes = 250,
                comments = 34,
                timestamp = 0
            )
        )
        return flowOf(PagingData.from(fakePosts))
    }
}
