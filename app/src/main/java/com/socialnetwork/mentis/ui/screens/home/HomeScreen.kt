package com.socialnetwork.mentis.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.socialnetwork.mentis.presentation.home.HomeViewModel
import com.socialnetwork.mentis.ui.screens.home.components.PostItem
import androidx.paging.compose.items

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val posts = viewModel.posts.collectAsLazyPagingItems()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(posts) { post ->
                post?.let {
                    PostItem(post = it)
                }
            }
        }

        if (posts.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
