package com.example.nineteen_2_0.data.database.db

import androidx.room.TypeConverter
import com.example.nineteen_2_0.data.gameitem.GameItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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