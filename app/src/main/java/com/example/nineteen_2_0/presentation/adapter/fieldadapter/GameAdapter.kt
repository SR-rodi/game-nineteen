package com.example.nineteen_2_0.presentation.adapter.fieldadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nineteen_2_0.data.gameitem.GameItem
import com.example.nineteen_2_0.databinding.ItemGameNotChoiceBinding

class GameAdapter(
    var itemGameList: List<GameItem>,
    private val choicePosition: (position: Int) -> Unit
) : RecyclerView.Adapter<GameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GameViewHolder(
        ItemGameNotChoiceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val gameItem = itemGameList[position]
        holder.bind(gameItem)
        holder.clickItem(gameItem) { positionList ->
            choicePosition(positionList)
        }
    }

    override fun getItemCount() = itemGameList.size
}
