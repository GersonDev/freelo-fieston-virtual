package com.spydevs.fiestonvirtual.data.entities.response

data class GalleryResponseEntity(
    val data: Data,
    val message: String
) {

    data class Data(
        val posts: List<Post>
    )

    data class Post(
        val idPost: Int,
        val postFile: String,
        val postStatus: Int,
        val postType: Int,
        val preview: String
    )
}