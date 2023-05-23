package ru.sr.nineteen.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.sr.nineteen.viewmodel.TrainingViewMod

fun trainingModule() = listOf(training())

private fun training() = module {
    viewModelOf(::TrainingViewMod)
}