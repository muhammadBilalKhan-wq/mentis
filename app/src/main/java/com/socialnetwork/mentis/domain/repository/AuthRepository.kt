package com.socialnetwork.mentis.domain.repository

import com.socialnetwork.mentis.domain.model.AuthResult

interface AuthRepository {
    suspend fun login(email: String, password: String): AuthResult
    suspend fun register(email: String, password: String): AuthResult
}