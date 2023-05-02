package ru.sr.nineteen.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.sr.nineteen.presentation.gamefield.GameFieldViewModel
import ru.sr.nineteen.presentation.rating.RatingViewModel
import ru.sr.nineteen.presentation.start.StartViewModel
import ru.sr.nineteen.presentation.training.TrainingViewModel

fun viewModelModule() = module {

    viewModelOf(::GameFieldViewModel)
    viewModelOf(::RatingViewModel)
    viewModelOf(::StartViewModel)
    viewModelOf(::TrainingViewModel)
}