package com.spydevs.fiestonvirtual.data.entities.response

data class MakeLikeResponseEntity(
    val data: Data,
    val message: String
) {
    data class Data(
        val userLikesPhotos: Int,
        val userLikesVideos: Int
    )
}