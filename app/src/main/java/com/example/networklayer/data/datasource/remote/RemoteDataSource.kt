package com.example.networklayer.data.datasource.remote

import com.example.apollo.GetCharactersQuery
import com.example.apollo.GetLocationQuery
import com.example.networklayer.data.commun.ResponseResult

interface RemoteDataSource {
    suspend fun getCharacters(page: Int): ResponseResult<GetCharactersQuery.Characters?>
    suspend fun getLocation(page: Int): ResponseResult<GetLocationQuery.Locations?>
}