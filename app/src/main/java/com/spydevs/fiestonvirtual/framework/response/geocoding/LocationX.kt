package com.spydevs.fiestonvirtual.framework.response.geocoding

import com.google.gson.annotations.SerializedName

data class LocationX(
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("lng")
    val longitude: Double
)