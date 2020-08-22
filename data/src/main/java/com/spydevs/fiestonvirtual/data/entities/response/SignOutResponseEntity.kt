package com.spydevs.fiestonvirtual.data.entities.response

data class SignOutResponseEntity(
    val data: Data,
    val message: String
) {
    data class Data(
        val rpta: Boolean
    )
}