package com.example.tickets

import android.app.Application
import com.example.tickets.app.presentation.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(dataSourceModules, cardModules, transportModules, numberOfDaysModule, numberOfTripsModule))
        }
    }
}