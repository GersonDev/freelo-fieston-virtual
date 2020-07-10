package com.spydevs.fiestonvirtual.ui.main.gallery

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryItem

sealed class GalleryResult {
    class Loading(val show: Boolean) : GalleryResult()
    class GetGallerySuccessful(val galleryItemList: List<GalleryItem>) : GalleryResult()
    class GetGalleryError(val errorResponse: ErrorResponse) : GalleryResult()
}