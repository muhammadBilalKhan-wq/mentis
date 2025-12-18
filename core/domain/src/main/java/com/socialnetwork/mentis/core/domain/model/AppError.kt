package com.socialnetwork.mentis.core.domain.model

sealed class AppError {
    data class NetworkError(val message: String? = null) : AppError()
    data class DatabaseError(val message: String? = null) : AppError()
    data class UnknownError(val message: String? = null) : AppError()
}