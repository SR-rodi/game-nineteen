package ru.sr.nineteen.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.sr.nineteen.data.UserIdProviderImpl
import ru.sr.nineteen.data.ValidationImpl
import ru.sr.nineteen.domain.UserIdProvider
import ru.sr.nineteen.domain.Validation
import ru.sr.nineteen.domain.gameitem.GetTokenUseCase
import ru.sr.nineteen.domain.gameitem.GetTokenUseCaseImpl

fun coreModule() = listOf(validationModule())

fun validationModule() = module {
    singleOf(::ValidationImpl) { bind<Validation>() }
    singleOf(::GetTokenUseCaseImpl) { bind<GetTokenUseCase>() }
    singleOf(::UserIdProviderImpl) { bind<UserIdProvider>() }
}