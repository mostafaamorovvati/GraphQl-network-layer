package com.example.networklayer.di

import com.example.networklayer.data.datasource.remote.rest.RestApiService
import com.example.networklayer.utils.REST_BASE_URL
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    factory { provideForecastApi(get()) }
    single { provideRetrofit() }
}


fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(REST_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideForecastApi(retrofit: Retrofit): RestApiService =
    retrofit.create(RestApiService::class.java)