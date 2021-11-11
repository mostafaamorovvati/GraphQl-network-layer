package com.example.networklayer.data.datasource.remote

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.ApolloQueryCall
import com.apollographql.apollo.api.Input
import com.example.apollo.GetCharactersQuery
import com.example.apollo.GetLocationQuery
import com.example.networklayer.utils.BASE_URL

object GraphQlApolloClient {

    private fun apolloClient(): ApolloClient =
        ApolloClient.builder().serverUrl(BASE_URL).build()

    fun getCharacters(page: Int): ApolloQueryCall<GetCharactersQuery.Data> =
        apolloClient().query(GetCharactersQuery())

    fun getLocation(page: Int): ApolloQueryCall<GetLocationQuery.Data> =
        apolloClient().query(GetLocationQuery())
}
