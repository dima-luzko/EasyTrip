package com.example.tickets.app.presentation.di

import com.example.tickets.app.data.repositoryImpl.CardRepositoryImpl
import com.example.tickets.app.data.repositoryImpl.NumberOfDaysRepositoryImpl
import com.example.tickets.app.data.repositoryImpl.NumberOfTripsRepositoryImpl
import com.example.tickets.app.data.repositoryImpl.TransportRepositoryImpl
import com.example.tickets.app.domain.repository.CardRepository
import com.example.tickets.app.domain.repository.NumberOfDaysRepository
import com.example.tickets.app.domain.repository.NumberOfTripsRepository
import com.example.tickets.app.domain.repository.TransportRepository
import com.example.tickets.app.presentation.viewModel.CardViewModel
import com.example.tickets.app.presentation.viewModel.NumberOfDaysViewModel
import com.example.tickets.app.presentation.viewModel.NumberOfTripsViewModel
import com.example.tickets.app.presentation.viewModel.TransportViewModel
import com.example.tickets.framework.remote.RemoteDataSource
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataSourceModules = module {
    single { RemoteDataSource() }
}

val viewModelModules = module {
    viewModel { CardViewModel(get()) }
    viewModel { TransportViewModel(get()) }
    viewModel { NumberOfDaysViewModel(get()) }
    viewModel { NumberOfTripsViewModel(get()) }
}

val repositoryModules = module {
    single<CardRepository> { CardRepositoryImpl(get()) }
    single<TransportRepository> { TransportRepositoryImpl(get()) }
    single<NumberOfDaysRepository> { NumberOfDaysRepositoryImpl(get() ) }
    single<NumberOfTripsRepository> { NumberOfTripsRepositoryImpl(get()) }
}

