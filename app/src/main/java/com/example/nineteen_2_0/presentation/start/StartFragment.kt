package com.example.nineteen_2_0.presentation.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.nineteen_2_0.R
import com.example.nineteen_2_0.data.gameitem.SettingGame
import com.example.nineteen_2_0.data.itemlist.RandomItemList
import com.example.nineteen_2_0.data.itemlist.TestList
import com.example.nineteen_2_0.databinding.FragmentStartBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StartFragment : Fragment() {

    private val viewModel: StartViewModel by viewModels()

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentStartBinding.inflate(inflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getGameList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.next.collect {
                binding.nextGameButton.isEnabled = it
            }
        }

        binding.randomNewGame.setListener {
            val settingGame =SettingGame()
            settingGame.list = RandomItemList().create()
            settingGame.gameMode = "random"
            findNavController().navigate(
                StartFragmentDirections.actionStartFragmentToGameFieldFragment(settingGame)
            )
        }

        binding.nextGameButton.setListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.list.collect { setting ->
                    findNavController().navigate(
                        StartFragmentDirections.actionStartFragmentToGameFieldFragment(setting)
                    )
                }
            }
        }

        binding.newGameButton.setListener {
            findNavController().navigate(
                StartFragmentDirections.actionStartFragmentToGameFieldFragment(SettingGame())
            )
        }

        binding.rattingButton.setListener {
            findNavController().navigate(
                StartFragmentDirections.actionStartFragmentToRattingFragment()
            )
        }

        binding.helpGameButton.setListener {
            findNavController().navigate(R.id.action_startFragment_to_trainingFragment)
        }
    }
}