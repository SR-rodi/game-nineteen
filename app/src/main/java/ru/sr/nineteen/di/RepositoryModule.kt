package ru.sr.nineteen.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.sr.mimeteen.database.repository.GameRepository
import ru.sr.mimeteen.database.repository.RatingRepository

fun repositoryModule() = module {

    singleOf(::GameRepository)
    singleOf(::RatingRepository)

}