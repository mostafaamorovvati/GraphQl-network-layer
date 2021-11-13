package com.example.networklayer.data.repository

import com.example.apollo.GetLocationQuery
import com.example.networklayer.data.datasource.remote.commun.ResponseResult
import com.example.networklayer.data.datasource.remote.graphql.GraphqlApiHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class LocationRepository(private val graphqlApiHelper: GraphqlApiHelper) {

    suspend fun getLocations(page: Int): Flow<ResponseResult<GetLocationQuery.Locations>> =
        flow {
            when (val result = graphqlApiHelper.getLocation(page)) {
                is ResponseResult.Success -> {
                    result.data?.let {
                        emit(ResponseResult.Success(it))
                    }
                }
                is ResponseResult.Error -> {
                    emit(ResponseResult.Error(result.exception))
                }
            }
        }.onStart {
            emit(ResponseResult.Loading)
        }

}