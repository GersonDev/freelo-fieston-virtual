package com.spydevs.fiestonvirtual.framework.api

import com.spydevs.fiestonvirtual.data.models.GalleryImageResponse
import com.spydevs.fiestonvirtual.domain.models.GalleryImageRequest
import com.spydevs.fiestonvirtual.framework.response.code.CodeResponseEntity
import retrofit2.http.*

/**
 * This API contents all end point of the services.
 */
interface FiestonVirtualApi {

    /**
     * This [codeEvent] is event code to input to application.
     * @return all data logged user.
     */
    @GET("consulta_codigo.php")
    suspend fun codeVerify(@Query("codInv") codeEvent: String): CodeResponseEntity

    @POST("user/{user_id}/cameraImage")
    suspend fun uploadImage(@Path("user_id") userId: Int,
                          @Body galleryImageRequest: GalleryImageRequest): NetworkResponse<GalleryImageResponse, String>
}