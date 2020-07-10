package com.spydevs.fiestonvirtual.ui.main.gallery

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryItem

sealed class GalleryResult {
    data class Loading(val show: Boolean) : GalleryResult()
    data class GetGallerySuccessful(val galleryItemList: List<GalleryItem>) : GalleryResult()
    data class GetGalleryError(val errorResponse: ErrorResponse) : GalleryResult()
}