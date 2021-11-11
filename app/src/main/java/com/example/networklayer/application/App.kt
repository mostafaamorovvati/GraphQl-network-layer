package com.example.networklayer.application

import android.app.Application
import com.example.networklayer.di.appModule
import com.example.networklayer.di.databaseModule
import com.example.networklayer.di.repositoryModule
import com.example.networklayer.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(repositoryModule, viewModelModule, databaseModule, appModule))
        }
    }
}