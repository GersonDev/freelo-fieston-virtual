package com.spydevs.fiestonvirtual.framework.datasource

import com.spydevs.fiestonvirtual.data.datasource.GalleryDataSource
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryImage
import com.spydevs.fiestonvirtual.domain.models.gallery.GalleryImageRequest
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.framework.api.FiestonVirtualApi
import com.spydevs.fiestonvirtual.framework.api.NetworkResponse
import com.spydevs.fiestonvirtual.framework.mapper.implementations.GalleryImageMapper

class GalleryDataSourceImpl(private val fiestonVirtualApi: FiestonVirtualApi): GalleryDataSource {
    override suspend fun uploadImage(
        userId: Int,
        galleryImageRequest: GalleryImageRequest
    ): ResultType<GalleryImage, String> {
        return when (val uploadImageNetworkResponse = fiestonVirtualApi.uploadImage(1, galleryImageRequest)) {
            is NetworkResponse.Success -> {
                val galleryImage = GalleryImageMapper.convertFromInitial(uploadImageNetworkResponse.body)
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
}