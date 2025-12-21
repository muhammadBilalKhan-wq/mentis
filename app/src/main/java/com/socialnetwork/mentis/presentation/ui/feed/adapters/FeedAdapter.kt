package com.socialnetwork.mentis.presentation.ui.feed.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.socialnetwork.mentis.core.domain.model.Post
import com.socialnetwork.mentis.databinding.ItemFeedBinding

class FeedAdapter : PagingDataAdapter<Post, FeedAdapter.FeedViewHolder>(POST_COMPARATOR) {
    companion object {
        private val POST_COMPARATOR = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class FeedViewHolder(val binding: ItemFeedBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val post = getItem(position)
        holder.binding.apply {
            tvUsername.text = post?.userName
            tvTime.text = post?.time
            tvContent.text = post?.content
            tvLikesCount.text = post?.likesCount.toString()
            tvCommentsCount.text = post?.commentsCount.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val binding = ItemFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedViewHolder(binding)
    }
}