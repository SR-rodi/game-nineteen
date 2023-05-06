package ru.sr.nineteen.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.sr.nineteen.viewmodel.RatingViewModel

fun ratingModule() = module {
    viewModelOf(::RatingViewModel)
}