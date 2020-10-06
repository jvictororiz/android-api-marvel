package com.joaororiz.desafio.android.di

import com.joaororiz.desafio.android.repository.CharactereRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CharactereRepository> {
        CharactereRepository.CharactereRepositoryImpl(service = get())
    }
}