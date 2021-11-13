package com.example.networklayer.presentation.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networklayer.data.commun.ResponseResult
import com.example.networklayer.data.repository.CharacterRepository
import com.example.networklayer.data.repository.PhotoRepository
import com.example.networklayer.data.models.CharactersModel
import com.example.networklayer.data.models.Photo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response

class CharactersViewModel(
    private val characterRepository: CharacterRepository,
    private val photoRepository: PhotoRepository
) : ViewModel() {

    val resultListCharacters = MutableLiveData<ResponseResult<CharactersModel>>()
    val resultPhoto = MutableLiveData<ResponseResult<Response<List<Photo>>>>()

    fun getListCharacters(page: Int) {
        viewModelScope.launch {

            characterRepository.getCharacters(page).collect {
                resultListCharacters.postValue(it)
            }

        }
    }

    fun getPhoto() {
        viewModelScope.launch {
            photoRepository.getPhoto().collect {
                resultPhoto.postValue(it)
            }
        }
    }
}