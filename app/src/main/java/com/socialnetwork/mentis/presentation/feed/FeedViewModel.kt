package com.socialnetwork.mentis.presentation.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.socialnetwork.mentis.data.repository.PostRepository
import com.socialnetwork.mentis.domain.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {

    val posts: Flow<PagingData<Post>> = postRepository.getPosts().map { pagingData ->
        pagingData.map { it.toPost() }
    }.cachedIn(viewModelScope)

    private val _refreshSignal = MutableStateFlow(false)
    val refreshSignal = _refreshSignal.asStateFlow()

    fun onRefresh() {
        viewModelScope.launch {
            _refreshSignal.emit(true)
        }
    }

    fun onRefreshHandled() {
        viewModelScope.launch {
            _refreshSignal.emit(false)
        }
    }
}
