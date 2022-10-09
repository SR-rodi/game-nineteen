package com.example.nineteen_2_0.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nineteen_2_0.data.database.entity.RattingEntity

@Dao
interface RatingDao{
    @Query("SELECT*FROM rating")
    fun getAll(): List<RattingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ratingEntity: RattingEntity)
}