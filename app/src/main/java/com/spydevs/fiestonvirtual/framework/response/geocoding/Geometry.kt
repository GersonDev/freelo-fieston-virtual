package com.spydevs.fiestonvirtual.framework.response.geocoding

import com.google.gson.annotations.SerializedName

data class Geometry(
    val bounds: Bounds,
    val location: LocationX,
    @SerializedName("location_type")
    val locationType: String,
    val viewport: Viewport
)