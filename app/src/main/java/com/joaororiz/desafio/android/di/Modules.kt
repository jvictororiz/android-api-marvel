package com.joaororiz.desafio.android.di

import com.joaororiz.desafio.android.BuildConfig
import com.joaororiz.desafio.android.data.service.MarvelService
import com.joaororiz.desafio.android.repository.CharacterRepository
import com.joaororiz.desafio.android.repository.datasource.character.CharacterRemoteDataSource
import com.joaororiz.desafio.android.repository.datasource.character.local.database.LocalDatabase
import com.joaororiz.desafio.android.repository.datasource.characters.remote.CharacterRemoteRemoteDataSource
import com.joaororiz.desafio.android.useCase.CharacterUseCase
import com.joaororiz.desafio.android.viewModel.main.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val viewModelModules = module {
    viewModel { MainViewModel(get(), androidApplication()) }
}

val repositoryModule = module {
    single<CharacterRepository> { CharacterRepository.CharacterRepositoryImpl(get(), get(), get()) }
}

val useCaseModule = module {
    single<CharacterUseCase> { CharacterUseCase.CharacterUseCaseImpl(get()) }
}

val dataSourceModule = module {
    single<CharacterRemoteDataSource> { CharacterRemoteRemoteDataSource(get()) }
}

val daoModule = module {
    single { databaseBuilder(androidContext()) }
    single { get<LocalDatabase>().characterDao() }
    single { get<LocalDatabase>().comicDao() }
}

val applicationModule = module {
    single { createHttpClient() }
    single { picasso(androidContext(), get()) }
    single { buildWebService(BuildConfig.SERVER_URL, get()) }
    single { get<Retrofit>().create(MarvelService::class.java)}
}
