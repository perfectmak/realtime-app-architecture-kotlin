package com.pusher.realtimearchitecture.data

import com.pusher.realtimearchitecture.data.pusher.PusherPostDataSource

/**
 * An implementation of PostsRepository that only returns realtime data
 *
 */
class RealtimePostsRepository(private val realtimeDataSource: PusherPostDataSource): PostsRepository {

    override fun fetchPosts() = realtimeDataSource.fetchPosts()
}