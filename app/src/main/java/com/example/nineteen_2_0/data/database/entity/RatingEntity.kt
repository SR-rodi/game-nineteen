package com.example.nineteen_2_0.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rating")
class RatingEntity (
    @ColumnInfo(name = "time")
    val time:Int,
    @ColumnInfo(name = "stepCount")
    val stepCount:Int,
    @PrimaryKey
    @ColumnInfo(name = "position")
    val id: Int? = null
        )