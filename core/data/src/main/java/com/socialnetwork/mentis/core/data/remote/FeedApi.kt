package com.socialnetwork.mentis.core.data.remote

import com.socialnetwork.mentis.core.data.remote.dto.PostDto
import com.socialnetwork.mentis.core.domain.model.AppError
import com.socialnetwork.mentis.core.domain.model.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.serialization.SerializationException
import javax.inject.Inject

class FeedApi @Inject constructor(private val httpClient: HttpClient) {

    suspend fun getFeed(page: Int, pageSize: Int): Result<List<PostDto>> {
        return try {
            val posts = httpClient.get("https://run.mocky.io/v3/64883955-0021-4158-9430-ebb4699895c8") { // Mock API endpoint
                parameter("page", page)
                parameter("limit", pageSize)
            }.body<List<PostDto>>()
            Result.Success(posts)
        } catch (e: ClientRequestException) {
            Result.Error(AppError.NetworkError("Client error: ${e.response.status.value}"))
        } catch (e: ServerResponseException) {
            Result.Error(AppError.NetworkError("Server error: ${e.response.status.value}"))
        } catch (e: SerializationException) {
            Result.Error(AppError.NetworkError("Serialization error: ${e.message}"))
        } catch (e: Exception) {
            Result.Error(AppError.NetworkError(e.message ?: "Unknown network error"))
        }
    }
}
