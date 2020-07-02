package com.spydevs.fiestonvirtual.framework.mapper.implementations

import com.spydevs.fiestonvirtual.data.entities.response.UploadFileResponseEntity
import com.spydevs.fiestonvirtual.domain.models.photo.Photo
import com.spydevs.fiestonvirtual.framework.mapper.Mapper

object UploadFileMapper: Mapper<UploadFileResponseEntity, Photo>() {
    override fun convertFromInitial(i: UploadFileResponseEntity): Photo {
        return Photo(i.data.post.idPost, i.data.post.postFile, i.message)
    }
}