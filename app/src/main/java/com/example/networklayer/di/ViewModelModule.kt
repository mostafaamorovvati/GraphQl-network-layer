package com.example.networklayer.di

import com.example.networklayer.presentation.characters.CharactersViewModel
import com.example.networklayer.presentation.details.DetailsViewModel
import com.example.networklayer.presentation.location.LocationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        CharactersViewModel(get(), get())
    }
    viewModel {
        DetailsViewModel()
    }
    viewModel {
        LocationViewModel(get())
    }

}