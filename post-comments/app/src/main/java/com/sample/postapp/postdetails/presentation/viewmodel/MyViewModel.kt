package com.sample.postapp.postdetails.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.postapp.postdetails.data.PostCommentRepository
import com.sample.postapp.postdetails.presentation.PostWithComments
import com.sample.postapp.postdetails.presentation.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val repository: PostCommentRepository
) : ViewModel() {

    private val pageSize = 10

    private val _postWithComments = MutableLiveData<Result<List<PostWithComments>>>()
    val postWithComments: LiveData<Result<List<PostWithComments>>> = _postWithComments

    private var data: ArrayList<PostWithComments> = ArrayList()
    fun getList() = data

    lateinit var postWithCommentsSelected: PostWithComments
    var isLoading = false

    fun postClicked(postWithComments: PostWithComments) {
        this.postWithCommentsSelected = postWithComments
    }



    fun fetchPostsAndComments() {
        // Check if minimum time has passed before making the next API call
        if (isLoading) {
            return
        }
        isLoading = true
        val currentPage = if (getList().size == 0) {
            1
        } else {
            getList().size / pageSize + 1
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                _postWithComments.postValue(Result.Loading)
                Log.e("tarun", "fetching data")
                val result = repository.getPostsAndComments(currentPage, pageSize)
                if (result is Result.Success) {
                    // Append the fetched data to the existing data
                    Log.e("tarun", "fetched successfully")
                    data.addAll(result.data)
                    _postWithComments.postValue(Result.Success(data.toList()))
                } else {
                    // Handle error case
                    _postWithComments.postValue(result)
                }
            } catch (e: Exception) {
                // Handle exception
                _postWithComments.postValue(Result.Error(e))
            } finally {
                isLoading = false
            }
        }
    }
}