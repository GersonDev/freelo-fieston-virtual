package com.spydevs.fiestonvirtual.domain.models.photo

import java.io.Serializable

data class Photo(
    var id: Int? = null,
    var urlPhoto: String? = null
) : Serializable