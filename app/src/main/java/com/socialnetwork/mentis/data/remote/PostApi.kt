package com.socialnetwork.mentis.data.remote

import com.socialnetwork.mentis.data.dto.PostDTO

interface PostApi {
    suspend fun getPosts(page: Int, limit: Int): List<PostDTO>
}
