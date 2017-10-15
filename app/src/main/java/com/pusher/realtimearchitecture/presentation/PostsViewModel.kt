package com.pusher.realtimearchitecture.presentation

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.pusher.realtimearchitecture.domain.FetchPosts
import com.pusher.realtimearchitecture.domain.dto.PostItems

/**
 * Feed View Model
 *
 */
class PostsViewModel(fetchPosts: FetchPosts): ViewModel() {

    var postItems = MediatorLiveData<PostItems>()

    init {
        postItems.addSource(fetchPosts.execute(), {
            postItems.setValue(it)
        })
    }
}