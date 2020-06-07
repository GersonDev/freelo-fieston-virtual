package com.spydevs.fiestonvirtual.framework.request

import com.google.gson.annotations.SerializedName

data class StarWarsRequest(
    @SerializedName("characters")
    val characters: List<String>
)