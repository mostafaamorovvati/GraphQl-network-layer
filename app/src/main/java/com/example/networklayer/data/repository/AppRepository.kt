package com.example.networklayer.data.repository

import com.example.apollo.GetLocationQuery
import com.example.networklayer.data.commun.ResponseResult
import com.example.networklayer.domain.models.CharactersModel
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    suspend fun getCharacters(page: Int): Flow<ResponseResult<CharactersModel>>
    suspend fun getLocations(page: Int): Flow<ResponseResult<GetLocationQuery.Locations>>
}