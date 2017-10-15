package com.pusher.realtimearchitecture.presentation

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.pusher.realtimearchitecture.data.RealtimePostsRepository
import com.pusher.realtimearchitecture.data.pusher.PusherPostDataSource
import com.pusher.realtimearchitecture.domain.FetchPosts
import java.lang.IllegalArgumentException

/**
 * View Model Factory in charge of creating View Models
 *
 * It does this by composing all the required dependencies needed by a ViewModel
 *
 */
class ViewModelFactory(private val lifecycleOwner: LifecycleOwner)
    : ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostsViewModel::class.java)) {
            val realtimeDataSource = PusherPostDataSource(lifecycleOwner)
            val realtimeFeedRepository = RealtimePostsRepository(realtimeDataSource)
            val fetchFeedUseCase = FetchPosts(realtimeFeedRepository)
            return PostsViewModel(fetchFeedUseCase) as T
        }
        throw IllegalArgumentException("Unknown View Model class name")
    }
}