package ru.sr.nineteen.presentation.win

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import ru.sr.nineteen.databinding.FragmentWinBinding
import ru.sr.nineteen.utility.BaseFragment
import ru.sr.nineteen.utility.setClickFromNavigate

class WinFragment : BaseFragment<FragmentWinBinding>() {

    override fun initBinding(inflater: LayoutInflater)= FragmentWinBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.winGame.setClickFromNavigate(WinFragmentDirections.actionWinFragmentToStartFragment())

    }
}