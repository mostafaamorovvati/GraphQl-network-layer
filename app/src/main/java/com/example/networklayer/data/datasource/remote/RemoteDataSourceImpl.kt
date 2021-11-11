package com.example.networklayer.data.datasource.remote

import com.apollographql.apollo.coroutines.await
import com.example.apollo.GetCharactersQuery
import com.example.apollo.GetLocationQuery
import com.example.networklayer.R
import com.example.networklayer.data.commun.DataSourceException
import com.example.networklayer.data.commun.ResponseResult

class RemoteDataSourceImpl : RemoteDataSource {

    override suspend fun getCharacters(page: Int): ResponseResult<GetCharactersQuery.Characters?> {
        return try {
            val result = GraphQlApolloClient.getCharacters(page).await()
            if (result.hasErrors()) {
                ResponseResult.Error(DataSourceException.Server(result.errors?.first()))
            } else {
                ResponseResult.Success(result.data?.characters)
            }
        } catch (e: Exception) {
            ResponseResult.Error(DataSourceException.Unexpected(R.string.error_unexpected_message))
        }
    }


    override suspend fun getLocation(page: Int): ResponseResult<GetLocationQuery.Locations?> {
        return try {
            val result = GraphQlApolloClient.getLocation(page).await()
            if (result.hasErrors()) {
                ResponseResult.Error(DataSourceException.Server(result.errors?.first()))
            } else {
                ResponseResult.Success(result.data?.locations)
            }
        } catch (e: Exception) {
            ResponseResult.Error(DataSourceException.Unexpected(R.string.error_unexpected_message))
        }
    }


}