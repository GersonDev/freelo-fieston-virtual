package com.spydevs.fiestonvirtual.data.entities.response

class CodeResponseEntity(
    var data: DataEntity? = null,
    var message: String? = null
) {
    data class DataEntity(
        var user: UserEntity? = null,
        var event: EventEntity? = null
    )

    data class UserEntity(
        var idUser: Int? = null
    )

    data class EventEntity(
        var userInvitationCode: Int? = null
    )
}

