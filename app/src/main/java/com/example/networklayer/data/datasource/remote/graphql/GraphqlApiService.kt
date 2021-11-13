package com.example.networklayer.data.datasource.remote.graphql

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.ApolloQueryCall
import com.example.apollo.GetCharactersQuery
import com.example.apollo.GetLocationQuery
import com.example.networklayer.utils.GRAPHQL_BASE_URL

object GraphqlApiService {

    private fun apolloClient(): ApolloClient =
        ApolloClient.builder().serverUrl(GRAPHQL_BASE_URL).build()

    fun getCharacters(page: Int): ApolloQueryCall<GetCharactersQuery.Data> =
        apolloClient().query(GetCharactersQuery())

    fun getLocation(page: Int): ApolloQueryCall<GetLocationQuery.Data> =
        apolloClient().query(GetLocationQuery())
}
