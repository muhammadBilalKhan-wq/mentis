package com.socialnetwork.mentis.core.domain.usecase

import androidx.paging.PagingData
import com.socialnetwork.mentis.core.domain.model.Post
import com.socialnetwork.mentis.core.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFeedPostsUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    operator fun invoke(): Flow<PagingData<Post>> {
        return postRepository.getFeedPosts()
    }
}
