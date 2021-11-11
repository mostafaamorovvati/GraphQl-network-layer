package com.example.networklayer.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.networklayer.domain.models.SingleCharacterModel

@Dao
interface AppDao {

    @Query("SELECT * FROM character")
    suspend fun getListCharacters(): List<SingleCharacterModel>

    @Insert(onConflict = REPLACE)
    suspend fun saveListCharacters(listTrivia: List<SingleCharacterModel>)
}