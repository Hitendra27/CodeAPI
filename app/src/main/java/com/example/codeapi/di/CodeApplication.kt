package com.example.codeapi.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CodeApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CodeApplication)
            modules((listOf(networkModule, viewModelModule)))
        }
    }
}