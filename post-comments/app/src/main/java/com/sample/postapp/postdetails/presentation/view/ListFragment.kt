package com.sample.postapp.postdetails.presentation.view

import PostAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.postapp.MainActivity
import com.sample.postapp.databinding.ListFragmentBinding
import com.sample.postapp.postdetails.presentation.OnClickListener
import com.sample.postapp.postdetails.presentation.PostWithComments
import com.sample.postapp.postdetails.presentation.Result
import com.sample.postapp.postdetails.presentation.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(), OnClickListener {

    private lateinit var binding: ListFragmentBinding
    private val viewModel: MyViewModel by activityViewModels()
    private val adapter = PostAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchPostsAndComments()
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && !viewModel.isLoading) {
                    // Reached bottom of list, load more data
                    viewModel.fetchPostsAndComments()
                }
            }
        })
    }

    private var firstVisibleItemPosition: Int = 0
    private var firstVisibleItemOffset: Int = 0
    private fun observeViewModel() {
        viewModel.postWithComments.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Success -> {
                    val layoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
                    firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                    val firstVisibleItemView =
                        layoutManager.findViewByPosition(firstVisibleItemPosition)
                    firstVisibleItemOffset = firstVisibleItemView?.top ?: 0

                    // Update the adapter
//                    adapter.appendData(result.data)
                    adapter.submitList(viewModel.getList())

                    // Restore the scroll position
                    val newLayoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
                    newLayoutManager.scrollToPositionWithOffset(
                        firstVisibleItemPosition,
                        firstVisibleItemOffset
                    )
                    hideLoadingIndicators()
                }

                is Result.Loading -> {
                    showLoadingIndicators()
                }

                is Result.Error -> {
                    hideLoadingIndicators()
                    Toast.makeText(
                        requireContext(),
                        "Error: ${result.exception.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Result.NoState -> {
                    // do nothing
                }
            }
        })
    }

    private fun showLoadingIndicators() {
        if (adapter.itemCount == 0) {
            binding.progressBar.visibility = View.VISIBLE
            binding.llProgressBarBottom.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.llProgressBarBottom.visibility = View.VISIBLE
        }
    }

    private fun hideLoadingIndicators() {
        binding.progressBar.visibility = View.GONE
        binding.llProgressBarBottom.visibility = View.GONE
    }

    override fun onClick(postWithComments: PostWithComments) {
        viewModel.postClicked(postWithComments)
        if (requireActivity() is MainActivity) {
            // Cast the parent activity to MainActivity
            val mainActivity = requireActivity() as MainActivity
            // Call the method in MainActivity to replace the fragment
            mainActivity.replaceFragment(DetailsFragment())
        }

    }
}