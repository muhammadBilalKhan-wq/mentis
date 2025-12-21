package com.socialnetwork.mentis.presentation.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.socialnetwork.mentis.databinding.FragmentFeedBinding
import com.socialnetwork.mentis.presentation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : BaseFragment<FragmentFeedBinding>() {

    private val viewModel: FeedViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentFeedBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: Use the ViewModel
    }
}
