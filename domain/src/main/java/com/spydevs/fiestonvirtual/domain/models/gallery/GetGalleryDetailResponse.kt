package com.spydevs.fiestonvirtual.domain.models.gallery

data class GetGalleryDetailResponse(
    val idPost: Int,
    val postType: Int,
    val postFile: String,
    val postTitle: String,
    val postStatus: Int,
    val postLikeCount: Int,
    val postLike: Boolean,
    val userName: String,
    val userImage: String
)