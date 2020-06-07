package com.spydevs.fiestonvirtual.domain.usecases

import com.spydevs.fiestonvirtual.domain.models.StarWars
import com.spydevs.fiestonvirtual.domain.repository.StarWarsRepository

class GetFilmsUseCase(private val repository: StarWarsRepository) {

    suspend fun getFilms(): StarWars {
        return repository.getFilms()
    }
}