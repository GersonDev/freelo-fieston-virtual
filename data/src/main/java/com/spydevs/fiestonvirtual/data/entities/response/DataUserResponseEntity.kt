package com.spydevs.fiestonvirtual.data.entities.response

data class DataUserResponseEntity(
    var data: DataEntity? = null,
    var mensaje: String? = null
) {
    data class DataEntity(
        var user: UserEntity? = null
    )

    data class UserEntity(
        var idUser: Int? = null,
        var userName: String? = null,
        var userLastName: String? = null,
        var userSurName: String? = null,
        var userEmail: String? = null,
        var userPhone: String? = null,
        var userCell: String? = null,
        var userInvitationCode: String? = null,
        var userTotalScore: String? = null,
        var userStatus: String? = null,
        var userLikePhotos: String? = null,
        var userLikeVideos: String? = null
    )
}

