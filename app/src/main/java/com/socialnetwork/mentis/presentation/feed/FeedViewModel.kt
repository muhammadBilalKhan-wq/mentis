package com.socialnetwork.mentis.presentation.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.socialnetwork.mentis.domain.model.FeedUiState
import com.socialnetwork.mentis.domain.usecase.GetFeedPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getFeedPostsUseCase: GetFeedPostsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FeedUiState(isLoading = true))
    val uiState: StateFlow<FeedUiState> = _uiState.asStateFlow()

    private var feedJob: Job? = null

    init {
        loadFeed()
    }

    fun loadFeed() {
        feedJob?.cancel()
        feedJob = viewModelScope.launch {
            getFeedPostsUseCase().collect {
                result ->
                result.onSuccess {
                    posts ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            posts = posts
                        )
                    }
                }.onFailure {
                    throwable ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = throwable.message ?: "An unexpected error occurred"
                        )
                    }
                }
            }
        }
    }
}
