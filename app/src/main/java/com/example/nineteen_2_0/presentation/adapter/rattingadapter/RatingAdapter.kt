package com.example.nineteen_2_0.presentation.adapter.rattingadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nineteen_2_0.data.database.entity.RattingEntity
import com.example.nineteen_2_0.databinding.ItemRatingBinding

class RatingAdapter(
    private val rattingList :List<RattingEntity>
) : RecyclerView.Adapter<RatingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RatingViewHolder(
        ItemRatingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        val rattingItem = rattingList[position]
        holder.bind(rattingItem,position )
    }

    override fun getItemCount()= rattingList.size
}