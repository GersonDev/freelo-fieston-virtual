package com.spydevs.fiestonvirtual.framework.api

import com.spydevs.fiestonvirtual.data.entities.response.GalleryImageResponseEntity
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryImageRequest
import com.spydevs.fiestonvirtual.data.entities.response.CodeResponseEntity
import com.spydevs.fiestonvirtual.data.entities.response.DataUserResponseEntity
import com.spydevs.fiestonvirtual.data.entities.response.ErrorResponseEntity
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
    suspend fun validateCode(
        @Query("codInv") codeEvent: String
    ): NetworkResponse<CodeResponseEntity, ErrorResponseEntity>

    //TODO add comments.
    @POST("user/{user_id}/cameraImage")
    suspend fun uploadImage(
        @Path("user_id") userId: Int,
        @Body galleryImageRequest: GalleryImageRequest
    ): NetworkResponse<GalleryImageResponseEntity, String>

    /**
     * This [userId] is the id user.
     * @return all data user.
     */
    @GET("detalle_usuario.php")
    suspend fun getDataUser(
        @Query("userId") userId: String
    ): NetworkResponse<DataUserResponseEntity, ErrorResponseEntity>
}