package com.example.networklayer.data.datasource.remote.rest

import com.example.networklayer.data.models.Photo
import retrofit2.Response
import retrofit2.http.GET


interface RestApiService {

    @GET("v2/list")
    suspend fun getPhotos(): Response<List<Photo>>

}
