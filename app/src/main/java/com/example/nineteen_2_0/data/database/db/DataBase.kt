package com.example.nineteen_2_0.data.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.nineteen_2_0.data.database.dao.GameDao
import com.example.nineteen_2_0.data.database.dao.RatingDao
import com.example.nineteen_2_0.data.database.entity.GameListEntity
import com.example.nineteen_2_0.data.database.entity.RatingEntity

@Database(
    entities = [
        GameListEntity::class,
        RatingEntity::class
    ], version = 1
)
@TypeConverters(ConverterList::class)
abstract class DataBase : RoomDatabase() {
    abstract fun gameDao(): GameDao
    abstract fun ratingDao(): RatingDao
}