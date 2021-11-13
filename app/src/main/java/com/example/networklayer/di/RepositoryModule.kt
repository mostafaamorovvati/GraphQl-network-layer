package com.example.networklayer.di

import com.example.networklayer.data.repository.CharacterRepository
import com.example.networklayer.data.repository.LocationRepository
import com.example.networklayer.data.repository.PhotoRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val repositoryModule = module {


    single {
        CharacterRepository(get(), androidApplication())
    }

    single {
        LocationRepository(get())
    }

    single { PhotoRepository(get()) }


}