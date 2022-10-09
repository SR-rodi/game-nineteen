package com.example.nineteen_2_0.presentation.rating

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.nineteen_2_0.R
import com.example.nineteen_2_0.databinding.FragmentRatingBinding
import com.example.nineteen_2_0.presentation.adapter.ratingadapter.RatingAdapter
import com.example.nineteen_2_0.utility.BaseFragment
import com.example.nineteen_2_0.utility.setClickFromNavigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RattingFragment : BaseFragment<FragmentRatingBinding>() {

    private val viewModel by viewModels<RatingViewModel>()

    override fun initBinding(inflater: LayoutInflater) = FragmentRatingBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getRatting()

        binding.backButton.setClickFromNavigate(RattingFragmentDirections.actionRattingFragmentToStartFragment())

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.ratingList.collect {
                binding.ratingRecycler.adapter = RatingAdapter(it)
            }
        }
    }
}