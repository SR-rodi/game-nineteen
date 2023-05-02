package com.example.nineteen_2_0.di

import com.example.nineteen_2_0.data.repository.GameRepository
import com.example.nineteen_2_0.data.repository.RatingRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun repositoryModule() = module {

    singleOf(::GameRepository)
    singleOf(::RatingRepository)

}