package ru.sr.nineteen.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.sr.nineteen.data.ValidationImpl
import ru.sr.nineteen.domain.Validation

fun coreModule() = listOf(validationModule())

fun validationModule() = module {
    singleOf(::ValidationImpl) { bind<Validation>() }
}