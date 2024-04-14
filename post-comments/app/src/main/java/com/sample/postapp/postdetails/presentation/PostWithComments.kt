package com.sample.postapp.postdetails.presentation

import com.sample.network.Comment
import com.sample.network.Post
import com.sample.network.User

data class PostWithComments(
    val post: Post,
    val comments: List<Comment>,
    val user: User?
)
