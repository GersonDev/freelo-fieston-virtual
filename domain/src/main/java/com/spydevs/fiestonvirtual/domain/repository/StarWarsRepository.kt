package com.spydevs.fiestonvirtual.domain.repository

import com.spydevs.fiestonvirtual.domain.models.StarWars

interface StarWarsRepository {
    suspend fun getFilms(): StarWars
}