package com.spydevs.fiestonvirtual.data.repository

import com.spydevs.fiestonvirtual.data.datasource.StarWarsDataSource
import com.spydevs.fiestonvirtual.domain.models.StarWars
import com.spydevs.fiestonvirtual.domain.repository.StarWarsRepository

class StarWarsRepositoryImpl(private val starWarsDataSource: StarWarsDataSource): StarWarsRepository {
    override suspend fun getFilms(): StarWars {
        return starWarsDataSource.getFilms()
    }
}