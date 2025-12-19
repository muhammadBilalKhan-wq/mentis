package com.socialnetwork.mentis.domain.usecase

import androidx.paging.PagingData
import com.socialnetwork.mentis.domain.model.Post
import com.socialnetwork.mentis.domain.repository.FeedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFeedPostsUseCaseImpl @Inject constructor(
    private val feedRepository: FeedRepository
) : GetFeedPostsUseCase {
    override operator fun invoke(): Flow<PagingData<Post>> {
        return feedRepository.getPosts()
    }
}
