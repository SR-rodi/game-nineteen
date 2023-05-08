package ru.sr.nineteen.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.sr.nineteen.data.mapper.ProfileDomainMapper
import ru.sr.nineteen.data.mapper.ProfileDomainMapperImpl
import ru.sr.nineteen.data.ProfileUserRepositoryImpl
import ru.sr.nineteen.domain.repository.ProfileUserRepository
import ru.sr.nineteen.domain.usecase.ChangePasswordUseCase
import ru.sr.nineteen.domain.usecase.DeleteProfileUserUseCase
import ru.sr.nineteen.domain.usecase.GetUserInfoUseCase
import ru.sr.nineteen.domain.usecase.LogOutUseCase
import ru.sr.nineteen.domain.usecase.UpdateUserUseCase
import ru.sr.nineteen.domain.usecase.impl.ChangePasswordUseCaseImpl
import ru.sr.nineteen.domain.usecase.impl.DeleteUserUseCaseImpl
import ru.sr.nineteen.domain.usecase.impl.GetUserInfoUseCaseImpl
import ru.sr.nineteen.domain.usecase.impl.LogOutUseCaseImpl
import ru.sr.nineteen.domain.usecase.impl.UpdateUserUseCaseImpl
import ru.sr.nineteen.presentation.model.mapper.ProfileUIMapper
import ru.sr.nineteen.presentation.model.mapper.ProfileUIMapperImpl
import ru.sr.nineteen.presentation.viewmodel.ProfileDeleteWarningViewModel
import ru.sr.nineteen.presentation.viewmodel.ProfileViewModel

fun profileModule() =
    listOf(profileViewModelModule(), profileRepositoryModule(), mapperModule(), useCaseModule())

fun profileViewModelModule() = module {
    viewModelOf(::ProfileViewModel)
    viewModelOf(::ProfileDeleteWarningViewModel)
}

fun profileRepositoryModule() = module {
    singleOf(::ProfileUserRepositoryImpl) { bind<ProfileUserRepository>() }
}

fun mapperModule() = module {
    singleOf(::ProfileDomainMapperImpl) { bind<ProfileDomainMapper>() }
    singleOf(::ProfileUIMapperImpl) { bind<ProfileUIMapper>() }
}

fun useCaseModule() = module {
    singleOf(::GetUserInfoUseCaseImpl) { bind<GetUserInfoUseCase>() }
    singleOf(::DeleteUserUseCaseImpl) { bind<DeleteProfileUserUseCase>() }
    singleOf(::LogOutUseCaseImpl) { bind<LogOutUseCase>() }
    singleOf(::UpdateUserUseCaseImpl) { bind<UpdateUserUseCase>() }
    singleOf(::ChangePasswordUseCaseImpl) { bind<ChangePasswordUseCase>() }
}