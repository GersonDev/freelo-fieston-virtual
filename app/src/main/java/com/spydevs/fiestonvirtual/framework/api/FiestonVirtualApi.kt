package com.spydevs.fiestonvirtual.framework.api

import com.spydevs.fiestonvirtual.data.entities.response.*
import com.spydevs.fiestonvirtual.domain.models.code.ValidateCodeRequest
import com.spydevs.fiestonvirtual.domain.models.comment.CommentRequest
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryImageRequest
import com.spydevs.fiestonvirtual.domain.models.trivia.TriviaRequest
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryRequest
import com.spydevs.fiestonvirtual.domain.models.user.GetRemoteUserRequest
import com.spydevs.fiestonvirtual.domain.models.welcome.WelcomeRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

/**
 * This API contents all end point of the services.
 */
interface FiestonVirtualApi {

    /**
     * This [validateCodeRequest]  content the event code to input to application.
     * @return all data logged user.
     */
    @POST("consulta_codigo.php")
    suspend fun validateCode(
        @Body validateCodeRequest: ValidateCodeRequest
    ): NetworkResponse<CodeResponseEntity, ErrorResponseEntity>

    //TODO add comments.
    @POST("user/{user_id}/cameraImage")
    suspend fun uploadImage(
        @Path("user_id") userId: Int,
        @Body galleryImageRequest: GalleryImageRequest
    ): NetworkResponse<GalleryImageResponseEntity, String>

    /**
     * This [getRemoteUserRequest] content the id user.
     * @return all data user.
     */
    @POST("detalle_usuario.php")
    suspend fun getDataUser(
        @Body getRemoteUserRequest: GetRemoteUserRequest
    ): NetworkResponse<DataUserResponseEntity, ErrorResponseEntity>

    /**
     * Upload any file to server in form-data
     * @param [file] an image, video or whatever media file
     * @param [name] name of the file
     * @return response about uploaded file
     */
    @Multipart
    @POST("index.php")
    suspend fun uploadFile(
        @Part file: MultipartBody.Part?,
        @Part("name") name: RequestBody?
    ): NetworkResponse<UploadImageResponseEntity, String>

    /**
     * Get event welcome
     * @param [welcomeRequest] a request object for event welcome
     * @return response about event welcome
     */
    @POST("detalle_evento.php")
    suspend fun getEventWelcome(
        @Body welcomeRequest: WelcomeRequest
    ): NetworkResponse<WelcomeResponseEntity, String>

    /**
     * Get trivia including questions and answers from web service.
     * @param [triviaRequest] a quest object for getting trivia
     * @return response about trivia
     */
    @POST("trivias.php")
    suspend fun getTrivia(
        @Body triviaRequest: TriviaRequest
    ): NetworkResponse<TriviaResponseEntity, String>


    /**
     * @param [galleryRequest] This object is necessary for return the gallery.
     * @return all the images and videos.
     */
    @POST("getGallery")
    fun getGallery(
        @Body galleryRequest: GalleryRequest
    ): NetworkResponse<GalleryResponseEntity, ErrorResponse>

    /**
     * @param [commentRequest] This object is necessary for return the comments.
     * @return all the comments.
     */
    @POST("getComments")
    fun getComments(
        @Body commentRequest: CommentRequest
    ): NetworkResponse<GetCommentsResponseEntity, ErrorResponse>
}