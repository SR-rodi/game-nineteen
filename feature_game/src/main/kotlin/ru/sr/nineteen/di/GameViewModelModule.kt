package ru.sr.nineteen.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.sr.nineteen.presentation.field.viewmodel.GameViewModel

fun gameModel() = listOf(gameViewModelModule())

fun gameViewModelModule() = module {
    viewModelOf(::GameViewModel)
}