package com.socialnetwork.mentis.presentation.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.socialnetwork.mentis.databinding.FragmentFeedBinding
import com.socialnetwork.mentis.presentation.ui.base.BaseFragment
import com.socialnetwork.mentis.presentation.ui.feed.adapters.FeedAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FeedFragment : BaseFragment<FragmentFeedBinding>(
    FragmentFeedBinding::inflate
) {

    private val viewModel: FeedViewModel by viewModels()
    private val feedAdapter = FeedAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreateView(inflater, container, savedInstanceState)

        setupRecyclerView()

        lifecycleScope.launch{
            viewModel.feedPagingData.collectLatest {
                feedAdapter.submitData(it)
            }
        }



        return binding.root
    }

    private fun setupRecyclerView() {
        binding.rvFeed.apply {
            adapter = feedAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }


}
