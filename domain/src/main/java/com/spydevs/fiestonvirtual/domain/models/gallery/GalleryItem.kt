package com.spydevs.fiestonvirtual.domain.models.gallery

import java.io.Serializable

class GalleryItem(
    var id: Int,
    var type: Int,
    var file: String,
    var status: Int
) : Serializable