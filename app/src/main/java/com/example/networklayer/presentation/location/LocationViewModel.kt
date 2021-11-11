package com.example.networklayer.presentation.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apollo.GetLocationQuery
import com.example.networklayer.data.commun.ResponseResult
import com.example.networklayer.data.repository.AppRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LocationViewModel(
    private val appRepository: AppRepository
) : ViewModel() {

    private val _resultListLocations =
        MutableLiveData<ResponseResult<GetLocationQuery.Locations>>()
    val resultListCharacters: LiveData<ResponseResult<GetLocationQuery.Locations>> =
        _resultListLocations

    fun getListLocations(page: Int) {
        viewModelScope.launch {

            appRepository.getLocations(page).collect {
                _resultListLocations.postValue(it)
            }
        }
    }

}