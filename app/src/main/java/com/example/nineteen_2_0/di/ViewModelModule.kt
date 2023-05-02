package com.example.nineteen_2_0.di

import com.example.nineteen_2_0.presentation.gamefield.GameFieldViewModel
import com.example.nineteen_2_0.presentation.rating.RatingViewModel
import com.example.nineteen_2_0.presentation.start.StartViewModel
import com.example.nineteen_2_0.presentation.training.TrainingViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

fun viewModelModule() = module {

    viewModelOf(::GameFieldViewModel)
    viewModelOf(::RatingViewModel)
    viewModelOf(::StartViewModel)
    viewModelOf(::TrainingViewModel)
}