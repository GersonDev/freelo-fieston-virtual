package com.spydevs.fiestonvirtual.domain.usecases.abstractions.gallery

interface UploadImageUseCase {
    suspend operator fun invoke(image: String): String
}