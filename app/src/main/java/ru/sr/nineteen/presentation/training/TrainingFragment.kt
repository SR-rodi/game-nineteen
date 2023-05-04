/*
package ru.sr.nineteen.presentation.training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.sr.nineteen.R
import ru.sr.nineteen.data.domain.gameitem.GameItemEngine
import ru.sr.nineteen.databinding.TrainingOneBinding
import ru.sr.nineteen.presentation.adapter.fieldadapter.GameAdapter
import ru.sr.BaseFragment
import ru.sr.nineteen.utility.setClickFromNavigate

class TrainingFragment : BaseFragment<TrainingOneBinding>() {

    private var counter = 1

    override fun initBinding(inflater: LayoutInflater) = TrainingOneBinding.inflate(inflater)

    private val viewModel by viewModel<TrainingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getTrainingScreen(viewModel.getItemListOne(), resources.getText(R.string.learn_one))

        setFieldAndText()

        binding.skipButton.setClickFromNavigate(TrainingFragmentDirections.actionTrainingFragmentToStartFragment())

    }

    private fun setFieldAndText() {
        binding.nextButton.setOnClickListener {
            counter++
            when (counter) {
                2 -> getTrainingScreen(
                    viewModel.getItemListTwo(),
                    resources.getText(R.string.learn_two)
                )
                3 -> getTrainingScreen(
                    viewModel.getItemListThree(),
                    resources.getText(R.string.learn_three)
                )
                4 -> getTrainingScreen(
                    viewModel.getItemListFour(),
                    resources.getText(R.string.learn_four)
                )
                5 -> getTrainingScreen(
                    viewModel.getItemListFive(),
                    resources.getText(R.string.learn_five)
                )
                else -> {
                    binding.trainingText.text = resources.getText(R.string.learn_six)
                    binding.nextButton.isEnabled = true
                    binding.trainingRecycler.isVisible = false
                }
            }
        }
    }

    private fun getTrainingScreen(itemList: MutableList<GameItemEngine>, text: CharSequence) {
        binding.trainingText.text = text
        binding.trainingRecycler.adapter = GameAdapter(itemList) {}
    }
}*/
