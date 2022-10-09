package com.example.nineteen_2_0.presentation.adapter.ratingadapter

import android.text.format.DateUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.nineteen_2_0.data.database.entity.RatingEntity
import com.example.nineteen_2_0.databinding.ItemRatingBinding

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
