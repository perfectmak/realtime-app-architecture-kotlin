package com.pusher.realtimearchitecture.presentation

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.pusher.realtimearchitecture.R
import com.pusher.realtimearchitecture.presentation.adapter.PostsListAdapter
import kotlinx.android.synthetic.main.activity_feed.*


class PostsActivity : AppCompatActivity() {

    private val postsListAdapter by lazy {
        PostsListAdapter(this)
    }

    private val postsViewModel: PostsViewModel by lazy {
        val viewModelFactory = ViewModelFactory(this)
        val viewModelProvider = ViewModelProviders.of(this, viewModelFactory)
        viewModelProvider.get(PostsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        postsListView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        postsListView.adapter = postsListAdapter

        postsViewModel.postItems.observe(this, Observer { items ->
            items?.also { postsListAdapter.items = it }
        })

    }
}
