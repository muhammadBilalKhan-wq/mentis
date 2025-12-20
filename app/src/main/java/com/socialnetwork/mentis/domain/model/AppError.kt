package com.socialnetwork.mentis.domain.model

sealed class AppError(val message: String) {
    class NetworkError(message: String) : AppError(message)
    class UnknownError(message: String) : AppError(message)
}
