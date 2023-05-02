/*
package ru.sr.nineteen.presentation.adapter.ratingadapter

import android.text.format.DateUtils
import androidx.recyclerview.widget.RecyclerView
import RatingEntity
import ru.sr.nineteen.databinding.ItemRatingBinding


class RatingViewHolder(private val binding: ItemRatingBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: RatingEntity, position: Int) {
        binding.apply {
            this.position.text = (position+1).toString()
            this.time.text = DateUtils.formatElapsedTime(item.time.toLong()).toString()
            this.step.text = item.stepCount.toString()
            this.mode.text = item.gameMode
        }
    }
}
*/
