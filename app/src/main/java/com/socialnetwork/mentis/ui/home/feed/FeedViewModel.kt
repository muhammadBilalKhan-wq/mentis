package com.socialnetwork.mentis.ui.home.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.socialnetwork.mentis.domain.model.Post
import com.socialnetwork.mentis.domain.repository.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(private val repository: FeedRepository) : ViewModel() {

    private val _feedPagingData = MutableStateFlow<PagingData<Post>>(PagingData.empty())
    val feedPagingData = _feedPagingData.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getPosts()
                .cachedIn(viewModelScope)
                .collectLatest {
                    _feedPagingData.value = it
                }
        }
    }
}
