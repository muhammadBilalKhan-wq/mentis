package com.socialnetwork.mentis.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.socialnetwork.mentis.data.repository.FeedRepositoryImpl
import com.socialnetwork.mentis.domain.usecase.GetFeedPostsUseCaseImpl
import com.socialnetwork.mentis.presentation.feed.FeedViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeedViewModel::class.java)) {
            val feedRepository = FeedRepositoryImpl()
            val getFeedPostsUseCase = GetFeedPostsUseCaseImpl(feedRepository)
            @Suppress("UNCHECKED_CAST")
            return FeedViewModel(getFeedPostsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
