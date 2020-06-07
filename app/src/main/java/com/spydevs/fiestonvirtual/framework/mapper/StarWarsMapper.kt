package com.spydevs.fiestonvirtual.framework.mapper

import com.spydevs.fiestonvirtual.domain.models.StarWars
import com.spydevs.fiestonvirtual.framework.response.StarWarsResponse

object StarWarsMapper: Mapper<StarWarsResponse, StarWars>() {

    override fun convertFromInitial(i: StarWarsResponse?): StarWars {
        return StarWars(i?.director)
    }

    override fun convertFromFinal(f: StarWars): StarWarsResponse {
        return StarWarsResponse()
    }

}