package com.pusher.realtimearchitecture.data.cache

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.pusher.realtimearchitecture.data.dto.PostModels

/**
 * Represents a local cached of posts loaded.
 * It uses Room to store and publish the data in realtime.
 *
 */
class LocalPostDataSource {

    fun fetchFeed(): LiveData<PostModels> {
        return MutableLiveData()
    }
}