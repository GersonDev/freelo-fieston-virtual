package com.spydevs.fiestonvirtual.framework.response.geocoding

import com.google.gson.annotations.SerializedName

data class GeocodingResponseEntity(
    @SerializedName("plus_code")
    val plusCode: PlusCode,
    val results: List<Result>,
    val status: String
)