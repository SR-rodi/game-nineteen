package ru.sr.nineteen.data.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.sr.nineteen.data.database.dao.GameDao
import ru.sr.nineteen.data.database.dao.RatingDao
import ru.sr.nineteen.data.database.entity.GameListEntity
import ru.sr.nineteen.data.database.entity.RatingEntity

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