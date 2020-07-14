package com.spydevs.fiestonvirtual.data.entities.response

data class GetCommentsResponseEntity(
    val data: Data,
    val message: String
) {
    data class Data(
        val comments: List<Comment>
    )

    data class Comment(
        val comment: String,
        val commentRegistrationDate: String,
        val commentStatus: Int,
        val idComment: Int,
        val idUserComment: Int,
        val userComment: String,
        val avatar: String
    )
}