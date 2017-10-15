package com.pusher.realtimearchitecture.data

import android.arch.lifecycle.LiveData
import com.pusher.realtimearchitecture.data.dto.PostModels

/**
 * Created by perfect on 9/14/17.
 */
interface PostsRepository {
    fun fetchPosts(): LiveData<PostModels>
}