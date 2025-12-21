package com.socialnetwork.mentis.presentation.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.socialnetwork.mentis.data.local.AppDatabase
import com.socialnetwork.mentis.domain.model.Post
import com.socialnetwork.mentis.domain.usecase.GetFeedPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    getFeedPostsUseCase: GetFeedPostsUseCase,
    private val appDatabase: AppDatabase
) : ViewModel() {

    companion object {
        private const val CACHE_TTL_MS = 15 * 60 * 1000L // 15 minutes
    }

    val posts: Flow<PagingData<Post>> = getFeedPostsUseCase().cachedIn(viewModelScope)

    private val _refreshSignal = MutableStateFlow(false)
    val refreshSignal: StateFlow<Boolean> = _refreshSignal.asStateFlow()

    init {
        viewModelScope.launch {
            val remoteKeys = appDatabase.remoteKeysDao().getRemoteKeys("feed")
            val lastUpdated = remoteKeys?.lastUpdated

            if (lastUpdated == null || (System.currentTimeMillis() - lastUpdated > CACHE_TTL_MS)) {
                _refreshSignal.value = true
            }
        }
    }

    fun onRefreshHandled() {
        _refreshSignal.value = false
    }
}
