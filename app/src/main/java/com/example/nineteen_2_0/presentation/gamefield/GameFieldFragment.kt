package com.example.nineteen_2_0.presentation.gamefield

import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.nineteen_2_0.R
import com.example.nineteen_2_0.databinding.FragmentGameFieldBinding
import com.example.nineteen_2_0.utility.notifyLineRemove
import com.example.nineteen_2_0.utility.notifyTwoPosition
import com.example.nineteen_2_0.presentation.adapter.fieldadapter.GameAdapter
import com.example.nineteen_2_0.utility.BaseFragment
import com.example.nineteen_2_0.utility.setClickFromNavigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class GameFieldFragment : BaseFragment<FragmentGameFieldBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentGameFieldBinding.inflate(inflater)

    private val viewModel by viewModels<GameFieldViewModel>()

    private val args by navArgs<GameFieldFragmentArgs>()

    private var helpList = mutableListOf<Int>()

    private val adapter by lazy {
        GameAdapter(viewModel.itemList) { position ->
            notifyAdapter(position)
        }
    }

    private fun notifyAdapter(position: Int) {
        val deleteList = viewModel.playGame(position)
        adapter.notifyItemChanged(position)
        if (deleteList.isNotEmpty()) {
            viewModel.counterStep()
            if (helpList.size == 2) {
                adapter.notifyTwoPosition(helpList.first(), helpList.last())
                helpList.clear()
            }
            adapter.notifyTwoPosition(
                deleteList[deleteList.lastIndex],
                deleteList[deleteList.lastIndex - 1]
            )
            if (deleteList.size >= 9)
                adapter.notifyLineRemove(deleteList)
        }
        if (viewModel.isWin.value) {
            viewModel.deleteDatabase()
            viewModel.addRatingDatabase(args.settingsGame.gameMode)
            findNavController().navigate(GameFieldFragmentDirections.actionGameFieldFragmentToWinFragment())
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.startSetting(args.settingsGame)

        binding.gameField.adapter = adapter

        setText(viewModel.mode)

        timer()

        clickAddButton()

        clickHelpButton()

        binding.goBack.setClickFromNavigate(GameFieldFragmentDirections.actionGameFieldFragmentToStartFragment())
    }

    private fun setText(gameMode: String) {
        when (gameMode) {
            "classic" -> binding.gameMode.text = "???????????????????????? ??????????"
            "random" -> binding.gameMode.text = "?????????????????? ??????????"
        }
    }


    private fun clickHelpButton() {
        binding.helpNew.setOnClickListener {
            val pair = viewModel.help()
            if (pair.first != pair.second) {
                adapter.notifyTwoPosition(pair.first, pair.second)
                binding.gameField.smoothScrollToPosition(pair.first)
                helpList.add(pair.first)
                helpList.add(pair.second)
            }
        }
    }

    private fun timer() {
        viewModel.startTimer()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.statistics.collect { statistics ->
                binding.timer.text =
                    DateUtils.formatElapsedTime(statistics.first.toLong()).toString()
                binding.stepCount.text = statistics.second.toString()
            }
        }
    }

    private fun clickAddButton() {
        binding.addButton.setOnClickListener {
            val pairPosition = viewModel.addList()
            adapter.notifyItemRangeInserted(pairPosition.first + 1, pairPosition.second)
            binding.gameField.smoothScrollToPosition(pairPosition.second)
        }
    }

    override fun onPause() {
        viewModel.addDatabase(args.settingsGame.gameMode)
        super.onPause()
    }
}