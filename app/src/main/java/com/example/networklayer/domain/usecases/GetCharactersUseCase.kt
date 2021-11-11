package com.example.networklayer.domain.usecases

import com.example.networklayer.data.repository.AppRepository

class GetCharactersUseCase(
    private val appRepository: AppRepository
) {
    suspend operator fun invoke(page: Int) = appRepository.getCharacters(page)
}