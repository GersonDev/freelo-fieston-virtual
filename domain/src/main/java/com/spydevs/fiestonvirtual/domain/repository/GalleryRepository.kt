package com.spydevs.fiestonvirtual.domain.repository

interface GalleryRepository {
    suspend fun uploadImage(base64Image: String): String
}