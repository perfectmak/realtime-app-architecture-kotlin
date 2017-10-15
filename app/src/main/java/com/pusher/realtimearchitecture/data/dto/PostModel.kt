package com.pusher.realtimearchitecture.data.dto

/**
 * Represents a Model that can be exchanged with the domain layer
 */
data class PostModel(val title: String, val description: String)

typealias PostModels = List<PostModel>