package com.example.networklayer.presentation.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networklayer.data.commun.ResponseResult
import com.example.networklayer.data.repository.AppRepository
import com.example.networklayer.domain.models.CharactersModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val appRepository: AppRepository
) : ViewModel() {

    private val _resultListCharacters = MutableLiveData<ResponseResult<CharactersModel>>()
    val resultListCharacters: LiveData<ResponseResult<CharactersModel>> = _resultListCharacters

    fun getListCharacters(page: Int) {
        viewModelScope.launch {

            appRepository.getCharacters(page).collect {
                _resultListCharacters.postValue(it)
            }
        }
    }
}