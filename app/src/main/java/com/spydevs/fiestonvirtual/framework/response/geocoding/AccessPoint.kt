package com.spydevs.fiestonvirtual.framework.response.geocoding

import com.google.gson.annotations.SerializedName

data class AccessPoint(
    @SerializedName("access_point_type")
    val accessPointType: String,
    val location: Location,
    @SerializedName("location_on_segment")
    val locationOnSegment: LocationOnSegment,
    @SerializedName("place_id")
    val placeId: String,
    @SerializedName("segment_position")
    val segmentPosition: Double,
    @SerializedName("unsuitable_travel_modes")
    val unsuitableTravelModes: List<Any>
)