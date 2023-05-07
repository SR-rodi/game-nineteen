package ru.sr.nineteen.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.sr.mimeteen.database.repository.GameRepository
import ru.sr.mimeteen.database.repository.RatingRepository
import ru.sr.nineteen.data.TokenProviderImpl
import ru.sr.nineteen.domain.TokenProvider

fun repositoryModule() = module {

    singleOf(::GameRepository)
    singleOf(::RatingRepository)

    single { Firebase.auth }

    singleOf(::TokenProviderImpl){bind<TokenProvider>()}

}