package com.example.networklayer.data.repository

import com.example.networklayer.data.commun.ResponseResult
import com.example.networklayer.data.datasource.remote.rest.RestApiHelper
import com.example.networklayer.data.models.Photo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import retrofit2.Response

class PhotoRepository(private val apiHelper: RestApiHelper) {

    suspend fun getPhoto(): Flow<ResponseResult<Response<List<Photo>>>> =
        flow {
            when (val result = apiHelper.getPhotos()) {
                is ResponseResult.Success -> emit(result.data.let { ResponseResult.Success(it) })
                is ResponseResult.Error -> emit(ResponseResult.Error(result.exception))
            }
        }.onStart {
            emit(ResponseResult.Loading)
        }
}