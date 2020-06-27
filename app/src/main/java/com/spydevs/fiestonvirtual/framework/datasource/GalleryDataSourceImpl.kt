package com.spydevs.fiestonvirtual.framework.datasource

import com.spydevs.fiestonvirtual.data.datasource.GalleryDataSource
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryImage
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryImageRequest
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryItem
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryRequest
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.framework.api.FiestonVirtualApi
import com.spydevs.fiestonvirtual.framework.api.NetworkResponse
import com.spydevs.fiestonvirtual.framework.mapper.frominitial.GalleryImageMapper

class GalleryDataSourceImpl(private val fiestonVirtualApi: FiestonVirtualApi) : GalleryDataSource {
    override suspend fun uploadImage(
        userId: Int,
        galleryImageRequest: GalleryImageRequest
    ): ResultType<GalleryImage, String> {
        return when (val uploadImageNetworkResponse =
            fiestonVirtualApi.uploadImage(1, galleryImageRequest)) {
            is NetworkResponse.Success -> {
                val galleryImage =
                    GalleryImageMapper.convertFromInitial(uploadImageNetworkResponse.body)
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
        return ResultType.Success(
            mutableListOf(
                GalleryItem(
                    1,
                    1,
                    "https://pngimg.com/uploads/face/face_PNG11760.png",
                    1
                ),
                GalleryItem(
                    2,
                    2,
                    "https://pngimg.com/uploads/face/face_PNG11760.png",
                    1
                ),
                GalleryItem(
                    3,
                    2,
                    "https://pngimg.com/uploads/face/face_PNG11760.png",
                    1
                ),
                GalleryItem(
                    4,
                    2,
                    "https://pngimg.com/uploads/face/face_PNG11760.png",
                    1
                ),
                GalleryItem(
                    5,
                    2,
                    "https://pngimg.com/uploads/face/face_PNG11760.png",
                    1
                )
            )
        )
        /*
        return when (val result = fiestonVirtualApi.getGallery(galleryRequest)) {
            is NetworkResponse.Success -> {
                ResultType.Success(GalleryItemListMapper.convertFromInitial(result.body.data.posts))
            }
            is NetworkResponse.ApiError -> {
                ResultType.Error(result.body)
            }
            is NetworkResponse.NetworkError -> {
                ResultType.Error(ErrorResponse(message = result.error.message))
            }
            is NetworkResponse.UnknownError -> {
                ResultType.Error(ErrorResponse(message = result.error.message))
            }
        }
         */
    }

}