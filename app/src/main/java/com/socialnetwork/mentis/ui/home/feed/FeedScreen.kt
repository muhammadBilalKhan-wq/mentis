package com.socialnetwork.mentis.ui.home.feed

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.socialnetwork.mentis.domain.model.Post
import com.socialnetwork.mentis.ui.home.post.PostItem

@Composable
fun FeedScreen() {
    val dummyPosts = listOf(
        Post("1", "user1", "", "Caption 1", 10, 5, 0L),
        Post("2", "user2", "", "Caption 2", 20, 10, 0L),
        Post("3", "user3", "", "Caption 3", 30, 15, 0L)
    )

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(dummyPosts) { post ->
            PostItem(post = post)
        }
    }
}
