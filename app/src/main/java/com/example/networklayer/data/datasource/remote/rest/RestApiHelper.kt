package com.example.networklayer.data.datasource.remote.rest

import com.example.networklayer.data.commun.ResponseResult
import com.example.networklayer.data.models.Photo
import retrofit2.Response

interface RestApiHelper {
    suspend fun getPhotos(): ResponseResult<Response<List<Photo>>>
}