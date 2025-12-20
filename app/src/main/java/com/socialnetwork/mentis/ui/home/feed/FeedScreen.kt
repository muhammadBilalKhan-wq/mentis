package com.socialnetwork.mentis.ui.home.feed

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.socialnetwork.mentis.ui.home.post.PostItem
import androidx.paging.compose.items

@Composable
fun FeedScreen(
    viewModel: FeedViewModel = hiltViewModel()
) {
    val feedPagingItems = viewModel.feedPagingData.collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(feedPagingItems) { post ->
            post?.let { PostItem(post = it) }
        }
    }
}
