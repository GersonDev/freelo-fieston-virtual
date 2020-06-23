package com.spydevs.fiestonvirtual.framework.api

import com.spydevs.fiestonvirtual.data.entities.response.CodeResponseEntity
import com.spydevs.fiestonvirtual.data.entities.response.GalleryImageResponseEntity
import com.spydevs.fiestonvirtual.data.entities.response.UploadImageResponseEntity
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryImageRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
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
                          @Body galleryImageRequest: GalleryImageRequest
    ): NetworkResponse<GalleryImageResponseEntity, String>

    @Multipart
    @POST("index.php")
    fun uploadFile(
        @Part file: MultipartBody.Part?,
        @Part("name") name: RequestBody?
    ): NetworkResponse<UploadImageResponseEntity, String>

}