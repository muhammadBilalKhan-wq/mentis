package com.socialnetwork.mentis.presentation.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.socialnetwork.mentis.data.repository.PostPagingSource
import com.socialnetwork.mentis.domain.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val postPagingSource: PostPagingSource
) : ViewModel() {

    val posts: Flow<PagingData<Post>> = Pager(PagingConfig(pageSize = 20)) {
        postPagingSource
    }.flow.cachedIn(viewModelScope)

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
