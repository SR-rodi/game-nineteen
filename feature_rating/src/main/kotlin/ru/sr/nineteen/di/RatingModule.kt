package ru.sr.nineteen.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.sr.nineteen.data.mapper.RatingDataMapper
import ru.sr.nineteen.data.mapper.RatingDomainMapper
import ru.sr.nineteen.data.mapper.RatingUiMapper
import ru.sr.nineteen.data.mapper.impl.RatingDataMapperImpl
import ru.sr.nineteen.data.mapper.impl.RatingDomainMapperImpl
import ru.sr.nineteen.data.mapper.impl.RatingUiMapperImpl
import ru.sr.nineteen.data.repository.RatingRemoteRepositoryImpl
import ru.sr.nineteen.domain.repositoty.RatingRemoteRepository
import ru.sr.nineteen.presentation.viewmodel.RatingViewModel

fun ratingModule() = listOf(ratingViewModelModule(), ratingMapperModule(),repositoryModule())

internal fun ratingViewModelModule() = module {
    viewModelOf(::RatingViewModel)
}

internal fun ratingMapperModule() = module {
    singleOf(::RatingDataMapperImpl) { bind<RatingDataMapper>() }
    singleOf(::RatingDomainMapperImpl) { bind<RatingDomainMapper>() }
    singleOf(::RatingUiMapperImpl) { bind<RatingUiMapper>() }
}

internal fun repositoryModule() = module {
    singleOf(::RatingRemoteRepositoryImpl) { bind<RatingRemoteRepository>() }
}