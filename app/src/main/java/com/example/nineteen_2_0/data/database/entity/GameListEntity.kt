package com.example.nineteen_2_0.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nineteen_2_0.data.gameitem.GameItem

@Entity(tableName = "game_list")
class GameListEntity(
    @ColumnInfo(name = "list")
    val list :List<GameItem> = emptyList(),
    @ColumnInfo(name = "time")
    val time:Int = 0,
    @ColumnInfo(name = "stepCount")
    val stepCount:Int = 1,
    @PrimaryKey
    @ColumnInfo(name = "position")
    val id: Int? = null
)