package com.spydevs.fiestonvirtual.data.entities.response

data class DataUserResponseEntity(
    var data: DataEntity,
    var message: String
) {
    data class DataEntity(
        var user: UserEntity
    )

    data class UserEntity(
        var idUser: Int,
        var idEvent: Int,
        var userName: String,
        var userLastName: String,
        var userSurName: String,
        var userEmail: String,
        var userPhone: String,
        var userCell: String,
        var userTotalScore: Int,
        var userStatus: Int,
        var avatar: String,
        var userLikesPhotos: Int,
        var userLikesVideos: Int,
        var userRanking: Int
    )

}

