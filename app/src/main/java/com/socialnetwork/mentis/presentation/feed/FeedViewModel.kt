package com.socialnetwork.mentis.presentation.feed

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.socialnetwork.mentis.domain.model.Post
import com.socialnetwork.mentis.domain.usecase.GetFeedPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    getFeedPostsUseCase: GetFeedPostsUseCase
) : ViewModel() {

    val posts: Flow<PagingData<Post>> = getFeedPostsUseCase().cachedIn(viewModelScope)

    init {
        Log.d("FeedViewModel", "ViewModel created")
    }
}
