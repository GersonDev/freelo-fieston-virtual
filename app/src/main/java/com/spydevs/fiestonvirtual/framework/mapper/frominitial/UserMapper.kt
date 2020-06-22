package com.spydevs.fiestonvirtual.framework.mapper.frominitial

import com.spydevs.fiestonvirtual.domain.models.User
import com.spydevs.fiestonvirtual.framework.database.entities.UserEntity
import com.spydevs.fiestonvirtual.framework.mapper.Mapper

object UserMapper : Mapper<User, UserEntity>() {

    override fun convertFromInitial(i: User?): UserEntity {
        return UserEntity().apply {
            firstName = i?.name
            lastName = i?.lastName
        }
    }

}