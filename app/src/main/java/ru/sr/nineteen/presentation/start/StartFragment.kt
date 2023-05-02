package ru.sr.nineteen.presentation.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.sr.nineteen.data.gameitem.SettingGame
import ru.sr.nineteen.databinding.FragmentStartBinding
import ru.sr.nineteen.utility.BaseFragment
import ru.sr.nineteen.utility.CustomButtonView
import ru.sr.nineteen.utility.setClickFromNavigate


class StartFragment : BaseFragment<FragmentStartBinding>() {

    private val viewModel by viewModel<StartViewModel> ()

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