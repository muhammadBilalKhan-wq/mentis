package com.socialnetwork.mentis.domain.usecase

import com.socialnetwork.mentis.domain.model.Post
import com.socialnetwork.mentis.domain.repository.FeedRepository
import kotlinx.coroutines.flow.Flow

class GetFeedPostsUseCaseImpl(
    private val feedRepository: FeedRepository
) : GetFeedPostsUseCase {
    override operator fun invoke(): Flow<Result<List<Post>>> {
        return feedRepository.getPosts()
    }
}
