/*
package ru.sr.nineteen.presentation.adapter.fieldadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.sr.nineteen.data.domain.gameitem.GameItemEngine
import ru.sr.nineteen.databinding.ItemGameNotChoiceBinding

class GameAdapter(
    var itemGameList: List<GameItemEngine>,
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
*/
