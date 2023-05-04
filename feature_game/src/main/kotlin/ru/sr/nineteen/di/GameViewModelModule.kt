package ru.sr.nineteen.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.sr.nineteen.presentation.viewmodel.GameViewModel

fun gameViewModelModule() = module {
    viewModelOf(::GameViewModel)
}