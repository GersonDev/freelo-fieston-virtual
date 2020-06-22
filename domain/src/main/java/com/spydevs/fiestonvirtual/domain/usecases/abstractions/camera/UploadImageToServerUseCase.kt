package com.spydevs.fiestonvirtual.domain.usecases.abstractions.camera

/**
 * Use case to upload a desire image to the server
 */
interface UploadImageToServerUseCase {

    /**
     * Invoke the action of uploading the image to the server
     * @param base64Image this is an converted image to base64
     * @return the response model from the server
     */
    suspend operator fun invoke(base64Image: String): String

}