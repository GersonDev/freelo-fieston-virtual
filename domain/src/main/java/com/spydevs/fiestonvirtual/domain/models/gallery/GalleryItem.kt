package com.spydevs.fiestonvirtual.domain.models.gallery

import java.io.Serializable

data class GalleryItem(
    var id: Int,
    var type: Int,
    var file: String,
    var status: Int,
    var preview: String? = null
) : Serializable {

    companion object {
        const val TYPE_PHOTO = 1
        const val TYPE_VIDEO = 2
    }

}