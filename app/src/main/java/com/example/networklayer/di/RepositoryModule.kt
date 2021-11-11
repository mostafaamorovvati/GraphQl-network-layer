package com.example.networklayer.di

import com.example.networklayer.domain.repository.AppRepositoryImpl
import com.example.networklayer.data.repository.AppRepository
import com.example.networklayer.domain.usecases.GetCharactersUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val repositoryModule = module {


    single<AppRepository> {
        AppRepositoryImpl(get(), androidApplication())
    }

    single { GetCharactersUseCase(get()) }


}