package com.socialnetwork.mentis.domain.use_case

import com.socialnetwork.mentis.domain.model.AuthResult
import com.socialnetwork.mentis.domain.repository.AuthRepository

class RegisterUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): AuthResult {
        return repository.register(email, password)
    }
}