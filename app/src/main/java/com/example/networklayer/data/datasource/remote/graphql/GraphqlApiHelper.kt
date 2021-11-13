package com.example.networklayer.data.datasource.remote.graphql

import com.example.apollo.GetCharactersQuery
import com.example.apollo.GetLocationQuery
import com.example.networklayer.data.datasource.remote.commun.ResponseResult

interface GraphqlApiHelper {
    suspend fun getCharacters(page: Int): ResponseResult<GetCharactersQuery.Characters?>
    suspend fun getLocation(page: Int): ResponseResult<GetLocationQuery.Locations?>
}