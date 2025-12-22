package com.socialnetwork.mentis.domain.model

import com.socialnetwork.mentis.R
import com.socialnetwork.mentis.core.ui.util.UiText

sealed class AppError {
    data class ApiException(val uiText: UiText) : AppError()
    data class PostCreationException(val message: String) : AppError()
}