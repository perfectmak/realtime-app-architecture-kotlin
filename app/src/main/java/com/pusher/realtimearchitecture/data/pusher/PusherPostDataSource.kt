package com.pusher.realtimearchitecture.data.pusher

import android.arch.lifecycle.*
import com.google.gson.Gson
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import com.pusher.realtimearchitecture.data.dto.PostModel
import com.pusher.realtimearchitecture.data.dto.PostModels
import com.pusher.realtimearchitecture.data.pusher.enitties.PusherPostEntity

/**
 * This represents a realtime data source for feeds.
 * It uses Pusher as the data source.
 *
 */
class PusherPostDataSource(private val lifecycleOwner: LifecycleOwner)
    : LifecycleObserver {

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    companion object {
        private const val PUSHER_API_KEY = "PUSHER_KEY_HERE"
        private const val PUSHER_APP_CLUSTER = "PUSHER_CLUSTER_HERE"
        private const val PUSHER_POST_CHANNEL = "posts-channel"
        private const val PUSHER_NEW_POST_EVENT = "new-posts"
    }

    private val pusher = Pusher(PUSHER_API_KEY, PusherOptions().setCluster(PUSHER_APP_CLUSTER))

    private var postModels: PostModels = mutableListOf()
    private val postsStream = MutableLiveData<PostModels>()


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onStart() {
        val channel = pusher.subscribe(PUSHER_POST_CHANNEL)
        channel.bind(PUSHER_NEW_POST_EVENT) { _, _, data ->
            val post = Gson().fromJson(data, PusherPostEntity::class.java)
            postModels = postModels.plus(PostModel(post.title, post.description))
            postsStream.postValue(postModels)
        }
    }

    fun fetchPosts() = postsStream

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() = pusher.connect()

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() = pusher.disconnect()

}