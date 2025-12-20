package com.socialnetwork.mentis.ui.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.socialnetwork.mentis.R
import com.socialnetwork.mentis.domain.model.Post

@Composable
fun PostItem(post: Post) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(data = post.user), // Assuming user is a URL
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = post.user, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "More options")
            }
        }
        Image(
            painter = rememberImagePainter(data = post.imageUrl),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )
        Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            IconButton(onClick = { }) {
                Icon(Icons.Filled.ThumbUp, contentDescription = "Like")
            }
            IconButton(onClick = { }) {
                Icon(Icons.Filled.Share, contentDescription = "Comment")
            }
            IconButton(onClick = { }) {
                Icon(Icons.Filled.Send, contentDescription = "Send")
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { }) {
                Icon(Icons.Filled.Bookmark, contentDescription = "Bookmark")
            }
        }
        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            Text(text = "${post.likes} likes", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = post.caption, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "View all ${post.comments} comments", style = MaterialTheme.typography.bodySmall)
        }
    }
}
