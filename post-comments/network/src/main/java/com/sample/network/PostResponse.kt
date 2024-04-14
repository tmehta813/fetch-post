package com.sample.network

// Post.kt
data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)

// Comment.kt
data class Comment(
    val id: Int,
    val postId: Int,
    val name: String,
    val email: String,
    val body: String
)

data class Album(
    val userId: Int,
    val id: Int,
    val title: String
)

data class Photo(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)

data class Todo(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company
)

data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: Geo
)

data class Geo(
    val lat: String,
    val lng: String
)

data class Company(
    val name: String,
    val catchPhrase: String,
    val bs: String
)
