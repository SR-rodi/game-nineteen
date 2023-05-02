package ru.sr.nineteen.presentation.gamefield

import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.sr.nineteen.databinding.FragmentGameFieldBinding
import ru.sr.nineteen.presentation.adapter.fieldadapter.GameAdapter
import ru.sr.nineteen.utility.BaseFragment
import ru.sr.nineteen.utility.notifyLineRemove
import ru.sr.nineteen.utility.notifyTwoPosition
import ru.sr.nineteen.utility.setClickFromNavigate

class GameFieldFragment : BaseFragment<FragmentGameFieldBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentGameFieldBinding.inflate(inflater)

    private val viewModel by viewModel<GameFieldViewModel>()

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
            "classic" -> binding.gameMode.text = "Классический режим"
            "random" -> binding.gameMode.text = "Случайный режим"
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