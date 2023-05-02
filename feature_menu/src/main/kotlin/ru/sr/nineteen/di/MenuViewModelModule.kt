package ru.sr.nineteen.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.sr.nineteen.presentation.viewmodel.MenuViewModel

fun menuViewModelModule() = module {

    viewModelOf(::MenuViewModel)
}