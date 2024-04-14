package com.sample.postapp.postdetails.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.postapp.postdetails.presentation.PostWithComments
import com.sample.postapp.postdetails.presentation.viewmodel.MyViewModel
import com.sample.postapp.databinding.DetailsFragmentBinding
import com.sample.network.Comment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val viewModel: MyViewModel by activityViewModels()
    private lateinit var binding: DetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        bindData(viewModel.postWithCommentsSelected)
    }

    private fun bindData(postWithComments: PostWithComments) {
        val post = postWithComments.post
        val comments = postWithComments.comments
        val user = postWithComments.user // Newly added user object

        binding.textTitle.text = post.title
        binding.textBody.text = post.body

        binding.textComments.text = "Comments: ${comments.size}"


        // Bind user details
        user?.let {
            binding.textUserName.text = user.name
            binding.textUserEmail.text = user.email
            binding.textUserWebsite.text = user.website
        }
        setAdapter(postWithComments.comments)
        // Bind other details as needed
    }

    // list of comments for a single post
    private fun setAdapter(comments: List<Comment>) {
        binding.recyclerViewComments.adapter = CommentAdapter(comments)
        binding.recyclerViewComments.layoutManager = LinearLayoutManager(requireContext())
    }

}