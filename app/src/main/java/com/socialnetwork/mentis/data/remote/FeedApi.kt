package com.socialnetwork.mentis.data.remote

import com.socialnetwork.mentis.data.dto.PostDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class FeedApi @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getPosts(page: Int, limit: Int): List<PostDto> {
        return client.get("https://jsonplaceholder.typicode.com/posts") {
            parameter("_page", page)
            parameter("_limit", limit)
        }.body()
    }
}
