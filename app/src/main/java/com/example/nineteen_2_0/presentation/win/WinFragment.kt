package com.example.nineteen_2_0.presentation.win

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.nineteen_2_0.databinding.FragmentWinBinding
import com.example.nineteen_2_0.utility.BaseFragment
import com.example.nineteen_2_0.utility.setClickFromNavigate

class WinFragment : BaseFragment<FragmentWinBinding>() {

    override fun initBinding(inflater: LayoutInflater)= FragmentWinBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.winGame.setClickFromNavigate(WinFragmentDirections.actionWinFragmentToStartFragment())

    }
}