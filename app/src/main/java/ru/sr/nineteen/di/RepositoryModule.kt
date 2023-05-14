package ru.sr.nineteen.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.sr.mimeteen.database.repository.FieldDBRepository
import ru.sr.nineteen.presentation.MainViewModel

fun repositoryModule() = module {

    viewModelOf(::MainViewModel)

    singleOf(::FieldDBRepository)
    single { Firebase.auth }
}