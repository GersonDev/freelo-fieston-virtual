package com.spydevs.fiestonvirtual.data.entities.response

data class UploadUserProfileImageResponseEntity(
    val `data`: Data,
    val message: String
) {
    data class Data(
        val post: Post
    ) {
        data class Post(
            val idPost: Int,
            val postFile: String,
            val postType: Int
        )
    }
}