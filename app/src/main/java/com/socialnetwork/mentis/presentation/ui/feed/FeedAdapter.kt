package com.socialnetwork.mentis.presentation.ui.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.socialnetwork.mentis.core.domain.model.Post
import com.socialnetwork.mentis.databinding.ItemFeedBinding

class FeedAdapter : ListAdapter<Post, FeedAdapter.FeedViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
    FeedViewHolder {
        val binding = ItemFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

    inner class FeedViewHolder(private val binding: ItemFeedBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.tvUsername.text = post.author
            binding.tvContent.text = post.text
            // The following fields do not exist in the Post data class, so they are commented out.
            // binding.tvTime.text = post.time
            // binding.tvLikesCount.text = "${post.likesCount} Likes"
            // binding.tvCommentsCount.text = "${post.commentsCount} Comments"
        }
    }

    class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post):
        Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post):
        Boolean {
            return oldItem == newItem
        }
    }
}
