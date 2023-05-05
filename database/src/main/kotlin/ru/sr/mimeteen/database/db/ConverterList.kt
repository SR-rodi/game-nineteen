package ru.sr.mimeteen.database.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.sr.nineteen.domain.gameitem.GameItemEngine

class ConverterList {

    @TypeConverter
    fun fromCountryLangList(value: List<List<GameItemEngine>>): String {
        val gson = Gson()
        val type = object : TypeToken<List<List<GameItemEngine>>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCountryLangList(value: String): List<List<GameItemEngine>> {
        val gson = Gson()
        val type = object : TypeToken<List<List<GameItemEngine>>>() {}.type
        return gson.fromJson(value, type)
    }
}