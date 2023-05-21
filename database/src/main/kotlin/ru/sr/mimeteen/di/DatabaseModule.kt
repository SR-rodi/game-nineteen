package ru.sr.mimeteen.di

import androidx.room.Room
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.sr.mimeteen.database.db.DataBase
import ru.sr.mimeteen.remotedatabase.UserProvider
import ru.sr.mimeteen.remotedatabase.UserProviderImpl
import ru.sr.mimeteen.remotedatabase.api.AuthApi
import ru.sr.mimeteen.remotedatabase.api.RatingApi
import ru.sr.mimeteen.remotedatabase.api.UploadApi
import ru.sr.mimeteen.remotedatabase.api.UserApi
import ru.sr.mimeteen.remotedatabase.api.impl.FireBaseAuthApi
import ru.sr.mimeteen.remotedatabase.api.impl.FireBaseStorageApi
import ru.sr.mimeteen.remotedatabase.api.impl.FirebaseRatingApi
import ru.sr.mimeteen.remotedatabase.api.impl.FirebaseUserApi

fun databaseModule() = listOf(remoteModule(), locationModule())

fun remoteModule() = module {
    val baseUrl = "https://emaillinkregistration-default-rtdb.asia-southeast1.firebasedatabase.app/"
    val baseUrlStorage = "gs://emaillinkregistration.appspot.com"

    val a = FirebaseDatabase.getInstance(baseUrl)
    single { FirebaseDatabase.getInstance(baseUrl) }

    single { Firebase.storage(baseUrlStorage).reference }

    singleOf(::FirebaseUserApi) { bind<UserApi>() }
    singleOf(::FireBaseStorageApi) { bind<UploadApi>() }
    singleOf(::UserProviderImpl) { bind<UserProvider>() }
    singleOf(::FireBaseAuthApi) { bind<AuthApi>() }
    singleOf(::FirebaseRatingApi) { bind<RatingApi>() }

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

