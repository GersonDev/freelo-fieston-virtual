package com.spydevs.fiestonvirtual.framework.mapper.from_initial

import com.spydevs.fiestonvirtual.domain.models.StarWars
import com.spydevs.fiestonvirtual.framework.mapper.Mapper
import com.spydevs.fiestonvirtual.framework.response.StarWarsResponse

object StarWarsMapper: Mapper<StarWarsResponse, StarWars>() {

    override fun convertFromInitial(i: StarWarsResponse?): StarWars {
        return StarWars(i?.director)
    }

}