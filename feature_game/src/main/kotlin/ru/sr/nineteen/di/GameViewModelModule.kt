package ru.sr.nineteen.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.sr.nineteen.data.mapper.GameItemDataMapper
import ru.sr.nineteen.data.mapper.GameRatingMapper
import ru.sr.nineteen.data.mapper.impl.GameItemDataMapperImpl
import ru.sr.nineteen.data.mapper.impl.GameRatingMapperImpl
import ru.sr.nineteen.data.repository.GameRepositoryImpl
import ru.sr.nineteen.domain.reposytory.GameRepository
import ru.sr.nineteen.domain.reposytory.RemoteRatingRepository
import ru.sr.nineteen.domain.reposytory.RemoteRatingRepositoryImpl
import ru.sr.nineteen.engin.GameEngin

import ru.sr.nineteen.presentation.field.viewmodel.GameViewModel

fun gameModel() = listOf(gameViewModelModule(), repositoryModule())

fun gameViewModelModule() = module {
    viewModelOf(::GameViewModel)

}

fun repositoryModule() = module {

/*    single { GameEngin.create() }*/

    singleOf(::GameItemDataMapperImpl) { bind<GameItemDataMapper>() }
    singleOf(::GameRatingMapperImpl) { bind<GameRatingMapper>() }

    singleOf(::GameRepositoryImpl) { bind<GameRepository>() }
    singleOf(::RemoteRatingRepositoryImpl) { bind<RemoteRatingRepository>() }
}

