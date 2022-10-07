package com.example.nineteen_2_0.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nineteen_2_0.data.database.entity.GameListEntity
import com.example.nineteen_2_0.data.database.entity.RatingEntity

@Dao
interface RatingDao{
    @Query("SELECT*FROM rating")
    fun getAll(): List<RatingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ratingEntity: RatingEntity)
}