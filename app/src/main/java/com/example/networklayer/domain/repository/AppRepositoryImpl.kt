package com.example.networklayer.domain.repository

import android.app.Application
import com.example.apollo.GetLocationQuery
import com.example.networklayer.data.commun.ResponseResult
import com.example.networklayer.data.datasource.local.AppDb
import com.example.networklayer.data.datasource.remote.RemoteDataSource
import com.example.networklayer.data.mappers.mapToDomainModel
import com.example.networklayer.data.repository.AppRepository
import com.example.networklayer.domain.models.CharactersModel
import com.example.networklayer.domain.models.InfoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class AppRepositoryImpl(
    private val remoteDataSource: RemoteDataSource, application: Application
) : AppRepository {

    private val db = AppDb.getDatabase(application)
    private val appDao = db.appDao()

    override suspend fun getCharacters(page: Int): Flow<ResponseResult<CharactersModel>> =
        flow {
            when (val result = remoteDataSource.getCharacters(page)) {
                is ResponseResult.Success -> {
                    result.data?.let {
                        it.mapToDomainModel().apply {
                            appDao.saveListCharacters(results)
                            emit(ResponseResult.Success(this))
                        }
                    }
                }
                is ResponseResult.Error -> {
                    val listCharacters = appDao.getListCharacters()
                    if (listCharacters.isNotEmpty()) {
                        emit(
                            ResponseResult.Success(
                                CharactersModel(
                                    InfoModel(),
                                    listCharacters
                                )
                            )
                        )
                    } else {
                        emit(ResponseResult.Error(result.exception))
                    }
                }
            }
        }.onStart {
            emit(ResponseResult.Loading)
        }

    override suspend fun getLocations(page: Int): Flow<ResponseResult<GetLocationQuery.Locations>> =
        flow {
            when (val result = remoteDataSource.getLocation(page)) {
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
