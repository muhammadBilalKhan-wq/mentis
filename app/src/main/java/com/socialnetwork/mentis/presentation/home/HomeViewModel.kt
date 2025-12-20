package com.socialnetwork.mentis.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.socialnetwork.mentis.domain.repository.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(repository: FeedRepository) : ViewModel() {

    val posts = repository.getPosts().cachedIn(viewModelScope)
}
