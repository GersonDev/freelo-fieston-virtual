package com.spydevs.fiestonvirtual.framework.response.geocoding

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("access_points")
    val accessPoints: List<AccessPoint>,
    @SerializedName("address_components")
    val addressComponents: List<AddressComponent>,
    @SerializedName("formatted_address")
    val formattedAddress: String,
    val geometry: Geometry,
    @SerializedName("place_id")
    val placeId: String,
    @SerializedName("plus_code")
    val plusCode: PlusCode,
    val types: List<String>
)