package com.example.nineteen_2_0.presentation.ratting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.nineteen_2_0.databinding.FragmentRattingBinding
import com.example.nineteen_2_0.presentation.adapter.rattingadapter.RatingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RattingFragment : Fragment() {

    private val viewModel by viewModels<RattingViewModel>()

    private var _binding: FragmentRattingBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentRattingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getRatting()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.rattingList.collect{
                binding.rattingRecycler.adapter = RatingAdapter(it)
            }
        }
    }

}