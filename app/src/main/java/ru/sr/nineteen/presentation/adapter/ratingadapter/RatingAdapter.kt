/*
package ru.sr.nineteen.presentation.adapter.ratingadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import RatingEntity
import ru.sr.nineteen.databinding.ItemRatingBinding

class RatingAdapter(
    private val ratingList :List<RatingEntity>
) : RecyclerView.Adapter<RatingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RatingViewHolder(
        ItemRatingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        val rattingItem = ratingList[position]
        holder.bind(rattingItem,position )
    }

    override fun getItemCount()= ratingList.size
}*/
