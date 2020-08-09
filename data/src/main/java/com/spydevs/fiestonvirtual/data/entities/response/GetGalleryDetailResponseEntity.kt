package com.spydevs.fiestonvirtual.data.entities.response

data class GetGalleryDetailResponseEntity(
    val data: Data,
    val message: String
) {
    data class Data(
        val user: User,
        val post: Post
    ) {

        data class User(
            val userName: String,
            val userImage: String
        )

        data class Post(
            val idPost: Int,
            val postFile: String,
            val postLike: Boolean,
            val postLikeCount: Int,
            val postStatus: Int,
            val postTitle: String,
            val postType: Int
        )
    }
}