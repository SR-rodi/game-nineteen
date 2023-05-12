package ru.sr.nineteen.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.sr.mimeteen.database.repository.RemoteRatingRepository
import ru.sr.mimeteen.database.repository.RemoteRatingRepositoryImpl
import ru.sr.nineteen.presentation.field.viewmodel.GameViewModel

fun gameModel() = listOf(gameViewModelModule())

fun gameViewModelModule() = module {
    viewModelOf(::GameViewModel)

    singleOf(::RemoteRatingRepositoryImpl) { bind<RemoteRatingRepository>() }
}