package com.spydevs.fiestonvirtual.data.entities.response

//TODO refactor when the service is correct.
class CodeResponseEntity(
    var data: DataEntity
) {
    data class DataEntity(
        var userInvitationCode: String? = null
    )
}

