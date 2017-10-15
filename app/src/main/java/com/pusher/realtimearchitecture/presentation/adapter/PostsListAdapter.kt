package com.pusher.realtimearchitecture.presentation.adapter

import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pusher.realtimearchitecture.R
import com.pusher.realtimearchitecture.domain.dto.PostItem
import com.pusher.realtimearchitecture.domain.dto.PostItems
import kotlinx.android.synthetic.main.feed_item.view.*

/**
 * Created by perfect on 9/13/17.
 */
class PostsListAdapter(private val context: Context): RecyclerView.Adapter<PostsListAdapter.ViewHolder>() {

    private var postList: PostItems? = null

    var items: PostItems
        get() = postList ?: emptyList()
        set(newList) {
            val oldList = items
            postList = newList

            DiffUtil.calculateDiff(object: DiffUtil.Callback() {
                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return false
                }

                override fun getOldListSize(): Int = oldList.size

                override fun getNewListSize(): Int = newList.size

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val oldItem = oldList[oldItemPosition]
                    val newItem = newList[newItemPosition]

                    return oldItem.title == newItem.title && oldItem.description == newItem.description
                }

            }).dispatchUpdatesTo(this)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        postList?.also { items -> holder.bindItem(items[position]) }
    }

    override fun getItemCount(): Int = postList?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val feedItemView = LayoutInflater.from(context).inflate(R.layout.feed_item, parent, false)
        return ViewHolder(feedItemView)
    }

    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        fun bindItem(item: PostItem) {
            itemView.titleTextView.text = item.title
            itemView.descriptionTextView.text = item.description
        }
    }
}