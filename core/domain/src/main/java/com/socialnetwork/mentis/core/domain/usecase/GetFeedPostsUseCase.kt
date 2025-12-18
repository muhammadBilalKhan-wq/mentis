package com.socialnetwork.mentis.core.domain.usecase

import com.socialnetwork.mentis.core.domain.model.Post
import com.socialnetwork.mentis.core.domain.model.Result

interface GetFeedPostsUseCase : UseCase<Unit, Result<List<Post>>>