package com.example.nineteen_2_0.presentation.win

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nineteen_2_0.databinding.FragmentWinBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WinFragment : Fragment() {

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

        binding.winGame.setListener {
            findNavController().navigate(WinFragmentDirections.actionWinFragmentToStartFragment())
        }
    }
}