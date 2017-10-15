package com.pusher.realtimearchitecture.domain.dto

/**
 * Represents a FeedItem to be exchanged with the Presentation Layer
 *
 */
data class PostItem(val title: String, val description: String)

typealias PostItems = List<PostItem>