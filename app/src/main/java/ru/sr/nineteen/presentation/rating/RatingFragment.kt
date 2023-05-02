/*
package ru.sr.nineteen.presentation.rating

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.sr.nineteen.databinding.FragmentRatingBinding
import ru.sr.nineteen.presentation.adapter.ratingadapter.RatingAdapter
import ru.sr.BaseFragment
import ru.sr.nineteen.utility.setClickFromNavigate

class RatingFragment : BaseFragment<FragmentRatingBinding>() {

    private val viewModel by viewModel<RatingViewModel>()

    override fun initBinding(inflater: LayoutInflater) = FragmentRatingBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getRatting()

        binding.backButton.setClickFromNavigate(RatingFragmentDirections.actionRattingFragmentToStartFragment())

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.ratingList.collect {
                binding.ratingRecycler.adapter = RatingAdapter(it)
            }
        }
    }
}*/
