package com.socialnetwork.mentis.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.socialnetwork.mentis.core.domain.usecase.GetFeedPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getFeedPostsUseCase: GetFeedPostsUseCase
) : ViewModel() {

    val feedPagingData = getFeedPostsUseCase().cachedIn(viewModelScope)

}
