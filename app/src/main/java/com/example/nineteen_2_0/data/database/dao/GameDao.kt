package com.example.nineteen_2_0.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nineteen_2_0.data.database.entity.GameListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT*FROM game_list")
    fun getAll(): Flow<GameListEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(gameListEntity: GameListEntity)

    @Query("DELETE FROM game_list")
    fun deleteAll()
}
