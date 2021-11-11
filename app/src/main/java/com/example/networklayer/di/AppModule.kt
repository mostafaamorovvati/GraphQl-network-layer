package com.example.networklayer.di

import com.example.networklayer.data.datasource.remote.RemoteDataSource
import com.example.networklayer.data.datasource.remote.RemoteDataSourceImpl
import org.koin.dsl.module

val appModule = module {

    single<RemoteDataSource> { RemoteDataSourceImpl() }

}



