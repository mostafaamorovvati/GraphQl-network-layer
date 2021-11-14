package com.example.networklayer.data.repository

import android.app.Application
import com.example.networklayer.data.datasource.remote.commun.ResponseResult
import com.example.networklayer.data.datasource.local.room.AppDb
import com.example.networklayer.data.datasource.remote.graphql.GraphqlApiHelper
import com.example.networklayer.data.mappers.mapToLocalModel
import com.example.networklayer.data.models.CharactersModel
import com.example.networklayer.data.models.InfoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class CharacterRepository(
    private val graphqlApiHelper: GraphqlApiHelper,
    application: Application
)  {

    private val db = AppDb.getDatabase(application)
    private val appDao = db.appDao()

     suspend fun getCharacters(page: Int): Flow<ResponseResult<CharactersModel>> =
        flow {
            when (val result = graphqlApiHelper.getCharacters(page)) {
                is ResponseResult.Success -> {
                    result.data?.let {
                        it.mapToLocalModel().apply {
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


        }