package com.spydevs.fiestonvirtual.framework.datasource

import com.spydevs.fiestonvirtual.data.datasource.GalleryDataSource
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryItem
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryRequest
import com.spydevs.fiestonvirtual.domain.models.gallery.GetGalleryDetailRequest
import com.spydevs.fiestonvirtual.domain.models.gallery.GetGalleryDetailResponse
import com.spydevs.fiestonvirtual.domain.models.photo.Photo
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.framework.api.FiestonVirtualApi
import com.spydevs.fiestonvirtual.framework.api.NetworkResponse
import com.spydevs.fiestonvirtual.framework.mapper.implementations.GalleryItemListMapper
import com.spydevs.fiestonvirtual.framework.mapper.implementations.UploadFileMapper
import okhttp3.MultipartBody

class GalleryDataSourceImpl(private val fiestonVirtualApi: FiestonVirtualApi) : GalleryDataSource {
    override suspend fun uploadImage(
        file: MultipartBody.Part, idUser: Int, eventId: Int, postType: Int
    ): ResultType<Photo, String> {
        return when (val uploadImageNetworkResponse =
            fiestonVirtualApi.uploadFile(file, idUser, eventId, postType)) {
            is NetworkResponse.Success -> {
                val galleryImage =
                    UploadFileMapper.convertFromInitial(uploadImageNetworkResponse.body)
                ResultType.Success(galleryImage)
            }
            is NetworkResponse.ApiError -> {
                ResultType.Error(uploadImageNetworkResponse.body)
            }
            is NetworkResponse.NetworkError -> {
                ResultType.Error(uploadImageNetworkResponse.error.message!!)
            }
            is NetworkResponse.UnknownError -> {
                ResultType.Error(uploadImageNetworkResponse.error.message!!)
            }
        }
    }

    override suspend fun getGallery(
        galleryRequest: GalleryRequest
    ): ResultType<List<GalleryItem>, ErrorResponse> {
        return when (val result = fiestonVirtualApi.getGallery(galleryRequest)) {
            is NetworkResponse.Success -> {
                ResultType.Success(GalleryItemListMapper.convertFromInitial(result.body.data.posts))
            }
            is NetworkResponse.ApiError -> {
                ResultType.Error(result.body)
            }
            is NetworkResponse.NetworkError -> {
                ResultType.Error(ErrorResponse(message = result.error.message ?: ""))
            }
            is NetworkResponse.UnknownError -> {
                ResultType.Error(ErrorResponse(message = result.error.message ?: ""))
            }
        }

    }

    override suspend fun getGalleryDetail(
        getGalleryDetailRequest: GetGalleryDetailRequest
    ): ResultType<GetGalleryDetailResponse, ErrorResponse> {
        return when (
            val result = fiestonVirtualApi.getGalleryDetail(getGalleryDetailRequest)) {
            is NetworkResponse.Success -> {
                ResultType.Success(
                    GetGalleryDetailResponse(
                        idPost = result.body.data.post.idPost,
                        postType = result.body.data.post.postType,
                        postFile = result.body.data.post.postFile,
                        postTitle = result.body.data.post.postTitle,
                        postStatus = result.body.data.post.postStatus,
                        postLikeCount = result.body.data.post.postLikeCount,
                        postLike = result.body.data.post.postLike,
                        userName = result.body.data.user.userName,
                        userImage = result.body.data.user.userImage
                    )
                )
            }
            is NetworkResponse.ApiError -> {
                ResultType.Error(result.body)
            }
            is NetworkResponse.NetworkError -> {
                ResultType.Error(ErrorResponse(message = result.error.message ?: ""))
            }
            is NetworkResponse.UnknownError -> {
                ResultType.Error(ErrorResponse(message = result.error.message ?: ""))
            }
        }
    }

}