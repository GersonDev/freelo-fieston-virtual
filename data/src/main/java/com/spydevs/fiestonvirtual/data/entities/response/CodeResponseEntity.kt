package com.spydevs.fiestonvirtual.data.entities.response

class CodeResponseEntity(
    var data: DataEntity,
    var message: String
) {
    data class DataEntity(
        var user: UserEntity,
        var event: EventEntity
    )

    data class UserEntity(
        var idUser: Int
    )

    data class EventEntity(
        var idEvent: Int
    )
}

