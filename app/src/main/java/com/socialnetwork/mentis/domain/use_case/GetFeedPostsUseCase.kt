package com.socialnetwork.mentis.domain.use_case

import com.socialnetwork.mentis.domain.repository.FeedRepository
import javax.inject.Inject

class GetFeedPostsUseCase @Inject constructor(
    private val repository: FeedRepository
) {
    operator fun invoke() = repository.getFeedPosts()
}
