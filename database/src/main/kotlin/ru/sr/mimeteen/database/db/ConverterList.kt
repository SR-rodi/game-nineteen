package ru.sr.mimeteen.database.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.sr.nineteen.gameitem.GameItemEngine

class ConverterList {

    @TypeConverter
    fun fromCountryLangList(value: List<List<ru.sr.nineteen.gameitem.GameItemEngine>>): String {
        val gson = Gson()
        val type = object : TypeToken<List<List<ru.sr.nineteen.gameitem.GameItemEngine>>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCountryLangList(value: String): List<List<ru.sr.nineteen.gameitem.GameItemEngine>> {
        val gson = Gson()
        val type = object : TypeToken<List<List<ru.sr.nineteen.gameitem.GameItemEngine>>>() {}.type
        return gson.fromJson(value, type)
    }
}