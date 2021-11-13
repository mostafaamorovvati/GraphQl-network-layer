package com.example.networklayer.di

import com.example.networklayer.data.datasource.remote.graphql.GraphqlApiHelper
import com.example.networklayer.data.datasource.remote.graphql.GraphqlApiHelperImpl
import org.koin.dsl.module

val appModule = module {

    single<GraphqlApiHelper> { GraphqlApiHelperImpl() }

}



