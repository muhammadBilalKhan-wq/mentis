package com.socialnetwork.mentis.data.repository

import com.socialnetwork.mentis.data.remote.AuthApi
import com.socialnetwork.mentis.domain.model.AuthResult
import com.socialnetwork.mentis.domain.repository.AuthRepository

class AuthRepositoryImpl(private val api: AuthApi) : AuthRepository {
    override suspend fun login(email: String, password: String): AuthResult {
        return try {
            api.login(email, password)
            AuthResult.Success
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "An unexpected error occurred")
        }
    }

    override suspend fun register(email: String, password: String): AuthResult {
        return try {
            api.register(email, password)
            AuthResult.Success
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "An unexpected error occurred")
        }
    }
}