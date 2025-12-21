package com.socialnetwork.mentis.presentation.ui.feed

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.socialnetwork.mentis.presentation.ui.feed.components.PostItem
import com.socialnetwork.mentis.presentation.viewmodel.FeedViewModel
import androidx.paging.compose.items

@Composable
fun FeedScreen(
    viewModel: FeedViewModel = hiltViewModel()
) {
    val posts = viewModel.feedPagingData.collectAsLazyPagingItems()
    LazyColumn {
        items(posts) { post ->
            post?.let { 
                PostItem(post = it)
            }
        }
    }
}
