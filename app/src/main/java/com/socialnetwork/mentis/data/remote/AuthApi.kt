package com.socialnetwork.mentis.data.remote

interface AuthApi {
    suspend fun login(email: String, password: String)
    suspend fun register(email: String, password: String)
}