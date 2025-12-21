package com.socialnetwork.mentis.core.domain.usecase

import com.socialnetwork.mentis.core.domain.repository.FeedRepository
import javax.inject.Inject

class GetFeedPostsUseCase @Inject constructor(private val repository: FeedRepository) {
    operator fun invoke() = repository.getFeedPosts()
}