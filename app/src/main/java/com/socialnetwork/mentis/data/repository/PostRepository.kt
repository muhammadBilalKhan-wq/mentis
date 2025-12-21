package com.socialnetwork.mentis.data.repository

import androidx.paging.PagingData
import com.socialnetwork.mentis.data.local.entity.PostEntity
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getPosts(): Flow<PagingData<PostEntity>>
}
