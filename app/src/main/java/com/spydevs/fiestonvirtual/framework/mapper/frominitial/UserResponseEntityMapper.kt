package com.spydevs.fiestonvirtual.framework.mapper.frominitial

import com.spydevs.fiestonvirtual.data.entities.response.DataUserResponseEntity
import com.spydevs.fiestonvirtual.domain.models.user.User
import com.spydevs.fiestonvirtual.framework.mapper.Mapper

object UserResponseEntityMapper : Mapper<DataUserResponseEntity.DataEntity, User>() {

    override fun convertFromInitial(i: DataUserResponseEntity.DataEntity?): User {
        return User().apply {
            id = i?.user?.idUser
            name = i?.user?.userName
            lastName = i?.user?.userLastName
        }
    }

}