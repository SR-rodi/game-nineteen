package com.example.nineteen_2_0.presentation.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.nineteen_2_0.data.gameitem.SettingGame
import com.example.nineteen_2_0.data.itemlist.RandomItemList
import com.example.nineteen_2_0.databinding.FragmentStartBinding
import com.example.nineteen_2_0.utility.BaseFragment
import com.example.nineteen_2_0.utility.CustomButtonView
import com.example.nineteen_2_0.utility.setClickFromNavigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StartFragment : BaseFragment<FragmentStartBinding>() {

    private val viewModel: StartViewModel by viewModels()

    override fun initBinding(inflater: LayoutInflater) = FragmentStartBinding.inflate(inflater)

    override fun onResume() {
        super.onResume()
        viewModel.getGameList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isEnableButtonNextGame(viewModel.next)

        setClickNextGame(binding.newGameButton, viewModel.list)

        binding.randomNewGame.setClickFromNavigate(
            StartFragmentDirections.actionStartFragmentToGameFieldFragment(viewModel.createRandomList())
        )

        binding.newGameButton.setClickFromNavigate(
            StartFragmentDirections.actionStartFragmentToGameFieldFragment(SettingGame())
        )

        binding.rattingButton.setClickFromNavigate(
            StartFragmentDirections.actionStartFragmentToRattingFragment()
        )

        binding.helpGameButton.setClickFromNavigate(
            StartFragmentDirections.actionStartFragmentToTrainingFragment()
        )
    }

    private fun isEnableButtonNextGame(isEnable: StateFlow<Boolean>) {
        viewLifecycleOwner.lifecycleScope.launch {
            isEnable.collect {
                binding.nextGameButton.isEnableButton = it
            }
        }
    }

    private fun setClickNextGame(
        view: CustomButtonView,
        flowSettingGame: StateFlow<SettingGame>
    ) {
        view.setListener {
            viewLifecycleOwner.lifecycleScope.launch {
                flowSettingGame.collect { setting ->
                    findNavController().navigate(
                        StartFragmentDirections.actionStartFragmentToGameFieldFragment(setting)
                    )
                }
            }
        }
    }
}