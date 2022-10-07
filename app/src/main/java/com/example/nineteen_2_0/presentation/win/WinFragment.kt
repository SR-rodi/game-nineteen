package com.example.nineteen_2_0.presentation.win

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.nineteen_2_0.R
import com.example.nineteen_2_0.databinding.FragmentStartBinding
import com.example.nineteen_2_0.databinding.FragmentWinBinding
import com.example.nineteen_2_0.presentation.start.StartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WinFragment : Fragment() {

    private val viewModel: WinViewModel by viewModels()

    private var _binding: FragmentWinBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWinBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonGetNewStart.setOnClickListener {
            findNavController().navigate(WinFragmentDirections.actionWinFragmentToStartFragment())
        }
    }
}