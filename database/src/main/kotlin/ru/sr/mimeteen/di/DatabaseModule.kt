package ru.sr.mimeteen.di

import androidx.room.Room
import com.google.firebase.database.FirebaseDatabase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.sr.mimeteen.database.db.DataBase
import ru.sr.mimeteen.remotedatabase.FirebaseUserApi
import ru.sr.mimeteen.remotedatabase.UserApi

fun databaseModule() = listOf(remoteModule(),locationModule())

fun remoteModule() = module {
    val baseUrl = "https://emaillinkregistration-default-rtdb.asia-southeast1.firebasedatabase.app/"

    single { FirebaseDatabase.getInstance(baseUrl) }

    singleOf(::FirebaseUserApi) { bind<UserApi>() }
}

fun locationModule() = module {

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