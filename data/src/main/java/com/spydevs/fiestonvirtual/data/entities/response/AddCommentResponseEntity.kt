package com.spydevs.fiestonvirtual.data.entities.response

data class AddCommentResponseEntity(
    val `data`: Data,
    val message: String
) {
    data class Data(
        val comment: Comment
    ) {
        data class Comment(
            val avatar: String,
            val comment: String,
            val commentRegistrationDate: String,
            val commentStatus: Int,
            val idComment: Int,
            val idUserComment: Int,
            val userComment: String
        )
    }
}