package com.socialnetwork.mentis.presentation.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.socialnetwork.mentis.domain.model.FeedUiState
import com.socialnetwork.mentis.domain.model.Post
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(FeedUiState(isLoading = true))
    val uiState: StateFlow<FeedUiState> = _uiState.asStateFlow()

    init {
        loadFeed()
    }

    fun loadFeed() {
        viewModelScope.launch {
            // Simulate network delay
            delay(1500)

            val stubbedPosts = listOf(
                Post(id = "1", author = "John Doe", content = "Just setting up my Mentis! #FirstPost"),
                Post(id = "2", author = "Jane Smith", content = "Hello World! Excited to be here."),
                Post(id = "3", author = "AndroidDev", content = "Jetpack Compose is awesome!"),
                Post(id = "4", author = "KotlinLover", content = "Exploring the new features in Kotlin 2.0."),
                Post(id = "5", author = "DesignMaven", content = "Working on a new design system. #UI #UX")
            )

            _uiState.update {
                it.copy(
                    isLoading = false,
                    posts = stubbedPosts
                )
            }
        }
    }
}
