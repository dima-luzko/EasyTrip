package com.example.tickets.app.presentation.di

import com.example.tickets.app.data.repositoryImpl.CardRepositoryImpl
import com.example.tickets.app.domain.repository.CardRepository
import com.example.tickets.app.presentation.CardViewModel
import com.example.tickets.framework.remote.RemoteDataSource
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cardModules = module {
    single { RemoteDataSource() }
    single<CardRepository> { CardRepositoryImpl(get()) }
    viewModel { CardViewModel(get()) }
}