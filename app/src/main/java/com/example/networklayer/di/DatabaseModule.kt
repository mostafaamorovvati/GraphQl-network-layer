package com.example.networklayer.di

import android.app.Application
import androidx.room.Room
import com.example.networklayer.data.datasource.local.room.AppDao
import com.example.networklayer.data.datasource.local.room.AppDb
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): AppDb {
        return Room.databaseBuilder(application, AppDb::class.java, "countries")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideCountriesDao(database: AppDb): AppDao {
        return database.appDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideCountriesDao(get()) }
}