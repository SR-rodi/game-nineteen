package ru.sr.nineteen.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.sr.nineteen.presentation.field.viewmodel.GameViewModel
import ru.sr.nineteen.presentation.win.viewmodel.WinViewModel

fun gameViewModelModule() = module {
    viewModelOf(::GameViewModel)
    viewModelOf(::WinViewModel)
}