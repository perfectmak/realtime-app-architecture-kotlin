package com.pusher.realtimearchitecture.domain

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.pusher.realtimearchitecture.data.PostsRepository
import com.pusher.realtimearchitecture.data.dto.PostModels
import com.pusher.realtimearchitecture.domain.dto.PostItem
import com.pusher.realtimearchitecture.domain.dto.PostItems

/**
 * Represents a use case in this application.
 * It would fetch feed from the appropriate repository and return it as a live data observable.
 *
 */
class FetchPosts(val postsRepo: PostsRepository) {

    fun execute(): LiveData<PostItems> {
        return Transformations.map(postsRepo.fetchPosts(), mapModelsToItems())
    }

    private fun mapModelsToItems(): (PostModels) -> PostItems {
        return { models ->
            models.map { PostItem(it.title, it.description) }
        }
    }
}