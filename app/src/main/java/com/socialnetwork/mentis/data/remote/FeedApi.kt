package com.socialnetwork.mentis.data.remote

import com.socialnetwork.mentis.data.dto.PostDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class FeedApi(private val client: HttpClient) {

    suspend fun getPosts(page: Int, limit: Int): List<PostDto> {
        return client.get("posts") {
            parameter("_page", page)
            parameter("_limit", limit)
        }.body()
    }
}
