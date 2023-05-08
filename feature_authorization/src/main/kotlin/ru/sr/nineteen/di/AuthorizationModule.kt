package ru.sr.nineteen.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.sr.nineteen.data.mapper.AuthDomainMapper
import ru.sr.nineteen.data.mapper.AuthDomainMapperImpl
import ru.sr.nineteen.data.mapper.AuthUiMapper
import ru.sr.nineteen.data.mapper.AuthUiMapperImpl
import ru.sr.nineteen.data.repository.AuthRepositoryImpl
import ru.sr.nineteen.domain.repository.AuthRepository
import ru.sr.nineteen.domain.usecase.CreateUserWithEmailAndPasswordUseCase
import ru.sr.nineteen.domain.usecase.PutTokenUseCase
import ru.sr.nineteen.domain.usecase.ResetPasswordUseCase
import ru.sr.nineteen.domain.usecase.SignInWithEmailUseCase
import ru.sr.nineteen.domain.usecase.impl.CreateUserWithEmailAndPasswordUseCaseImpl
import ru.sr.nineteen.domain.usecase.impl.PutTokenUseCaseImpl
import ru.sr.nineteen.domain.usecase.impl.ResetPasswordUseCaseImpl
import ru.sr.nineteen.domain.usecase.impl.SignInWithEmailUseCaseImpl
import ru.sr.nineteen.presentation.registration.viewmodel.RegistrationViewModel
import ru.sr.nineteen.presentation.resetpassword.viewmodel.ResetPasswordViewModel
import ru.sr.nineteen.presentation.signin.viewmodel.SignInViewModel

fun authorizationModule() =
    listOf(repositoryModule(), networkModule(), useCaseModule(), mapperModule(),viewModelModule())

fun repositoryModule() = module {
    singleOf(::AuthRepositoryImpl) { bind<AuthRepository>() }
}

fun networkModule() = module {
    single { Firebase.auth }
}

fun useCaseModule() = module {
    singleOf(::CreateUserWithEmailAndPasswordUseCaseImpl) { bind<CreateUserWithEmailAndPasswordUseCase>() }
    singleOf(::SignInWithEmailUseCaseImpl) { bind<SignInWithEmailUseCase>() }
    singleOf(::PutTokenUseCaseImpl){bind<PutTokenUseCase>()}
    singleOf(::ResetPasswordUseCaseImpl){bind<ResetPasswordUseCase>()}
}

fun mapperModule() = module {
    singleOf(::AuthDomainMapperImpl) { bind<AuthDomainMapper>() }
    singleOf(::AuthUiMapperImpl) { bind<AuthUiMapper>() }
}

fun viewModelModule() = module {
    viewModelOf(::SignInViewModel)
    viewModelOf(::RegistrationViewModel)
    viewModelOf(::ResetPasswordViewModel)
}