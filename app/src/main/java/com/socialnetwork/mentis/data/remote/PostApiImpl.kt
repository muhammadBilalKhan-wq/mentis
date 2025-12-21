package com.socialnetwork.mentis.data.remote

import com.socialnetwork.mentis.data.dto.PostDTO
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class PostApiImpl @Inject constructor(private val client: HttpClient) : PostApi {
    override suspend fun getPosts(page: Int, limit: Int): List<PostDTO> {
        return client.get("https://my-json-server.typicode.com/typicode/demo/posts") { // Adjusted the URL to a common placeholder
            parameter("page", page)
            parameter("limit", limit)
        }
    }
}
