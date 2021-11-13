package com.example.networklayer.data.datasource.remote.rest

import com.example.networklayer.R
import com.example.networklayer.data.datasource.remote.commun.DataSourceException
import com.example.networklayer.data.datasource.remote.commun.ResponseResult
import com.example.networklayer.data.models.Photo
import retrofit2.Response

class RestApiHelperImpl(private val apiService: RestApiService) : RestApiHelper {

    override suspend fun getPhotos(): ResponseResult<Response<List<Photo>>> {
        return try {
            val result = apiService.getPhotos()
            if (result.isSuccessful) {
                ResponseResult.Success(result)
            } else {
                ResponseResult.Error(DataSourceException.Server(result.message()))
            }
        } catch (e: Exception) {
            ResponseResult.Error(DataSourceException.Unexpected(R.string.error_unexpected_message))
        }
    }


}