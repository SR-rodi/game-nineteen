package ru.sr.nineteen.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.sr.nineteen.data.repository.GameRepository
import ru.sr.nineteen.data.repository.RatingRepository

fun repositoryModule() = module {

    singleOf(::GameRepository)
    singleOf(::RatingRepository)

}