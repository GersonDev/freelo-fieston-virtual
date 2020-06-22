package com.spydevs.fiestonvirtual.framework.datasource

import com.spydevs.fiestonvirtual.data.datasource.GalleryDataSource
import com.spydevs.fiestonvirtual.domain.models.GalleryImage
import com.spydevs.fiestonvirtual.domain.models.GalleryImageRequest
import com.spydevs.fiestonvirtual.domain.resource.Resource
import com.spydevs.fiestonvirtual.framework.api.FiestonVirtualApi
import com.spydevs.fiestonvirtual.framework.api.NetworkResponse
import com.spydevs.fiestonvirtual.framework.mapper.frominitial.GalleryImageMapper

class GalleryDataSourceImpl(private val fiestonVirtualApi: FiestonVirtualApi): GalleryDataSource {
    override suspend fun uploadImage(image: String, galleryImageRequest: GalleryImageRequest): Resource<GalleryImage> {
        return when (val uploadImageNetworkResponse = fiestonVirtualApi.uploadImage(1, galleryImageRequest)) {
            is NetworkResponse.Success -> {
                //We use mapper un data sources
                val galleryImage = GalleryImageMapper.convertFromInitial(uploadImageNetworkResponse.body)
                Resource.success(galleryImage)
            }
            is NetworkResponse.ApiError -> {
                Resource.error(uploadImageNetworkResponse.body, null)
            }
            is NetworkResponse.NetworkError -> {
                Resource.error(uploadImageNetworkResponse.error.message!!, null)
            }
            is NetworkResponse.UnknownError -> {
                Resource.error(uploadImageNetworkResponse.error.message!!, null)
            }
        }
    }
}