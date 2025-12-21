package com.socialnetwork.mentis.data.remote

import com.socialnetwork.mentis.data.dto.PostDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi {
    @GET("posts")
    suspend fun getPosts(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): List<PostDTO>
}
