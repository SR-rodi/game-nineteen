package ru.sr.nineteen.data.database.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.sr.nineteen.domain.gameitem.GameItem

class ConverterList {

    @TypeConverter
    fun fromCountryLangList(value: List<GameItem>): String {
        val gson = Gson()
        val type = object : TypeToken<List<GameItem>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCountryLangList(value: String): List<GameItem> {
        val gson = Gson()
        val type = object : TypeToken<List<GameItem>>() {}.type
        return gson.fromJson(value, type)
    }
}