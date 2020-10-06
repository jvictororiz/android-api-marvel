package com.joaororiz.desafio.android.di

import com.joaororiz.desafio.android.viewModel.ComicCharactersViewModel
import com.joaororiz.desafio.android.viewModel.DetailedCharactersViewModel
import com.joaororiz.desafio.android.viewModel.ListComicViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { ListComicViewModel(get()) }
    viewModel { DetailedCharactersViewModel(get()) }
    viewModel { ComicCharactersViewModel(get()) }
}