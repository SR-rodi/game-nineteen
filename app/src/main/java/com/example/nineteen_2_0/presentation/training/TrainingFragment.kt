package com.example.nineteen_2_0.presentation.training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.nineteen_2_0.R
import com.example.nineteen_2_0.data.gameitem.GameItem
import com.example.nineteen_2_0.databinding.TrainingOneBinding
import com.example.nineteen_2_0.presentation.adapter.fieldadapter.GameAdapter
import com.example.nineteen_2_0.utility.BaseFragment
import com.example.nineteen_2_0.utility.setClickFromNavigate

class TrainingFragment : BaseFragment<TrainingOneBinding>() {

    private var counter = 1

    override fun initBinding(inflater: LayoutInflater) = TrainingOneBinding.inflate(inflater)

    private val viewModel by viewModels<TrainingViewModel>()

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

    private fun getTrainingScreen(itemList: MutableList<GameItem>, text: CharSequence) {
        binding.trainingText.text = text
        binding.trainingRecycler.adapter = GameAdapter(itemList) {}
    }
}