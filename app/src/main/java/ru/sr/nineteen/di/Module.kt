package ru.sr.nineteen.di

import androidx.room.Room
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.sr.nineteen.data.database.db.DataBase


fun dataBaseModule() = module {

    single(named("database")) {
        Room.databaseBuilder(
            get(),
            DataBase::class.java,
            "list"
        ).build()
    }

    single {
        get<DataBase>(named("database")).gameDao()
    }


    single {
        get<DataBase>(named("database")).ratingDao()
    }

}