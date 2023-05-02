package com.example.nineteen_2_0.presentation.rating

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.nineteen_2_0.databinding.FragmentRatingBinding
import com.example.nineteen_2_0.presentation.adapter.ratingadapter.RatingAdapter
import com.example.nineteen_2_0.utility.BaseFragment
import com.example.nineteen_2_0.utility.setClickFromNavigate
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RattingFragment : BaseFragment<FragmentRatingBinding>() {

    private val viewModel by viewModel<RatingViewModel>()

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