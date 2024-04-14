package com.sample.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("posts")
    suspend fun getPosts(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int
    ): List<Post>

    @GET("posts/{postId}/comments")
    suspend fun getComments(
        @Path("postId") postId: Int,
        @Query("_page") page: Int, // Add the query parameter for page
        @Query("_limit") limit: Int // Add the query parameter for limit
    ): List<Comment>

    @GET("posts/{postId}/comments")
    suspend fun getComments(
        @Path("postId") postId: Int
    ): List<Comment>


    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") userId: Int): User

}


