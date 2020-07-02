package com.spydevs.fiestonvirtual.domain.usecases.implementations.gallery

import com.spydevs.fiestonvirtual.domain.models.photo.Photo
import com.spydevs.fiestonvirtual.domain.repository.GalleryRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.gallery.UploadImageUseCase
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class UploadImageUseCaseImpl(private val galleryRepository: GalleryRepository) :
    UploadImageUseCase {
    override suspend fun invoke(imagePath: String): ResultType<Photo, String> {
        //TODO get used and event from their repositories respectively
        val file = File(imagePath)
        val requestBody = RequestBody.create(MediaType.parse("image/*"), file)
        val fileUploadMultiPart = MultipartBody.Part.createFormData("file", file.name, requestBody)
        return galleryRepository.uploadImage(fileUploadMultiPart, 5, 3, 1)
    }
}