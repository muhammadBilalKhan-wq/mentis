package com.socialnetwork.mentis.core.data.remote

import com.socialnetwork.mentis.core.data.remote.dto.PostDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class FeedApi @Inject constructor(private val httpClient: HttpClient) {

    suspend fun getFeedPosts(page: Int, limit: Int): List<PostDto> {
        return httpClient.get("https://run.mocky.io/v3/64883955-0021-4158-9430-ebb4699895c8") { // Mock API endpoint
            parameter("page", page)
            parameter("limit", limit)
        }.body()
    }
}
