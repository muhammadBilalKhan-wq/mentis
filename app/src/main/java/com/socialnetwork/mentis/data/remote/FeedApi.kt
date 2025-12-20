package com.socialnetwork.mentis.data.remote

import com.socialnetwork.mentis.data.remote.dto.PostDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class FeedApi(private val client: HttpClient) {

    suspend fun getPosts(): List<PostDto> {
        return client.get(BASE_URL).body()
    }

    companion object {
        private const val BASE_URL = "https://my-json-server.typicode.com/sayyed-basith/mentis-fake-api/posts"
    }
}
