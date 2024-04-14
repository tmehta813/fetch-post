package com.sample.postapp.postdetails.data

import android.util.Log
import com.sample.postapp.postdetails.presentation.PostWithComments
import com.sample.postapp.postdetails.presentation.Result
import com.sample.network.ApiService
import com.sample.network.Comment
import com.sample.network.Post
import com.sample.network.User
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class PostCommentRepository @Inject constructor(
    private val apiService: ApiService,
) {
    companion object {
        const val COMMENT_LIMIT = 2
        const val PAGE = 1
    }

    private suspend fun getPosts(page: Int, limit: Int): List<Post> {
        return apiService.getPosts(page, limit)
    }

    private suspend fun getComments(
        postId: Int,
        page: Int = PAGE,
        limit: Int = COMMENT_LIMIT
    ): List<Comment> {

        // here in practical or real world if we've thousands of comments,
        // then we can limit here, for eg fetch only 5 or 10 first
        return apiService.getComments(postId)
    }


    suspend fun getPostsAndComments(page: Int, limit: Int): Result<List<PostWithComments>> {
        return try {
            // Fetch posts for the given page and limit
            val posts = getPosts(page, limit)

            // Use coroutineScope to launch concurrent jobs for fetching comments in parallel
            val commentsList = coroutineScope {
                posts.map { post ->
                    async { getComments(post.id) }
                }.map { it.await() }
            }

            // similarly for users in post concurrent jobs for fetching
            val userList = coroutineScope {
                posts.map { post ->
                    async { getUsersForPosts(post) }
                }.map { it.await() }
            }

            // Combine posts with comments, to improve user experiences , making parallel api calls
            val postWithComments = posts.zip(commentsList) { post, comments ->
                val user = userList.firstOrNull { it.id == post.userId }
                PostWithComments(post, comments, user)
            }

            Result.Success(postWithComments)
        } catch (e: Exception) {
            Result.Error(e) // Handle error case
        }
    }

    private suspend fun getUsersForPosts(post: Post): User {
        return apiService.getUser(post.userId)
    }


}

