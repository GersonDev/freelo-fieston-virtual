package com.spydevs.fiestonvirtual.data.datasource

import com.spydevs.fiestonvirtual.domain.models.StarWars

interface StarWarsDataSource {

    suspend fun getFilms(): StarWars
}