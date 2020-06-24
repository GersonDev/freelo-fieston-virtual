package com.spydevs.fiestonvirtual.data.entities.response

//TODO refactor when the service is correct.
data class DataUserResponseEntity(
    var data: DataEntity? = null,
    var mensaje: String? = null
) {
    data class DataEntity(
        var idEvent: String? = null,
        var idUser: String? = null,
        var imaProUser: Any? = null,
        var userCell: String? = null,
        var userEmail: String? = null,
        var userLastName: String? = null,
        var userLikesPhotos: String? = null,
        var userLikesVideos: String? = null,
        var userName: String? = null,
        var userPhone: String? = null,
        var userRegistrationDate: String? = null,
        var userStatus: String? = null,
        var userSurName: String? = null,
        var userTotalScore: String? = null
    )
}

