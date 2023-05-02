package ru.sr.nineteen.presentation.adapter.fieldadapter

import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ru.sr.nineteen.R
import ru.sr.nineteen.data.gameitem.GameItem
import ru.sr.nineteen.data.gameitem.StatusItem
import ru.sr.nineteen.databinding.ItemGameNotChoiceBinding

class GameViewHolder(
    private val binding: ItemGameNotChoiceBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(gameItem: GameItem) {
        binding.button.text = gameItem.number.toString()
        when (gameItem.statusItem) {
            StatusItem.CHOICE -> {
                binding.button.setBackgroundResource(R.color.blue_300)
                binding.button.alpha = 0.2f
            }
            StatusItem.HELP -> {
                binding.button.alpha = 1f
                binding.button.setBackgroundResource(R.drawable.bg_item_help)
                binding.itemCard.startAnimation(
                    AnimationUtils.loadAnimation(
                        binding.button.context, R.anim.help_anim
                    )
                )
            }
            StatusItem.NOT_VISIBLE -> binding.itemCard.isVisible = false
            StatusItem.NOT_CHOICE -> {
                binding.button.alpha = 1f
                binding.button.setBackgroundResource(R.color.blue_100)
            }
            StatusItem.SELECT -> {
                binding.button.alpha = 1f
                binding.button.setBackgroundResource(R.color.blue_400)
            }
        }
    }

    fun clickItem(
        gameItem: GameItem,
        choicePosition: (positionList: Int) -> Unit
    ) {
        binding.button.setOnClickListener {
            if (gameItem.statusItem != StatusItem.CHOICE) choicePosition(adapterPosition)
        }
    }

}