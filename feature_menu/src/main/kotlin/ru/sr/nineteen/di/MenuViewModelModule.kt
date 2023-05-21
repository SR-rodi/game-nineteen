package ru.sr.nineteen.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.sr.nineteen.presentation.viewmodel.MenuViewModel

fun menuModule() = listOf(menuViewModelModule())

fun menuViewModelModule() = module {

    viewModelOf(::MenuViewModel)
}