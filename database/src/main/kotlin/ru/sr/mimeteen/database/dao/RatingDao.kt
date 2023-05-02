package ru.sr.mimeteen.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.sr.nineteen.data.database.entity.RatingEntity

@Dao
interface RatingDao{
    @Query("SELECT*FROM rating")
    fun getAll(): List<RatingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ratingEntity: RatingEntity)
}