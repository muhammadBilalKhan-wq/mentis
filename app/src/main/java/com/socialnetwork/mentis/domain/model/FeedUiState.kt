package com.socialnetwork.mentis.domain.model

/**
 * Represents the UI state for the Feed screen.
 *
 * @param posts the list of posts to display.
 * @param isLoading true if the feed is currently loading, false otherwise.
 * @param error the error message to display, if any.
 */
data class FeedUiState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) {
    /**
     * Returns true if the feed is empty, false otherwise.
     */
    val isEmpty: Boolean
        get() = posts.isEmpty()
}