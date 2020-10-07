package com.joaororiz.desafio.android.di

import com.joaororiz.desafio.android.BuildConfig
import com.joaororiz.desafio.android.data.service.MarvelService
import com.joaororiz.desafio.android.repository.CharactereRepository
import com.joaororiz.desafio.android.repository.datasource.characteres.CharactereDataSource
import com.joaororiz.desafio.android.repository.datasource.characteres.remote.CharacterRemoteDataSource
import com.joaororiz.desafio.android.viewModel.main.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { MainViewModel(get(), androidApplication()) }
}

val repositoryModule = module {
    single<CharactereRepository> { CharactereRepository.CharactereRepositoryImpl(get()) }
}

val dataSourceModule = module {
    single<CharactereDataSource> { CharacterRemoteDataSource(get()) }
}

val applicationModule = module {
    single { createHttpClient() }
    single { picasso(androidContext(), get()) }
    single { buildWebService<MarvelService>(BuildConfig.SERVER_URL, get()) }
}
