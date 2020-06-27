package com.spydevs.fiestonvirtual.framework.mapper.implementations

import com.spydevs.fiestonvirtual.domain.models.user.User
import com.spydevs.fiestonvirtual.framework.database.entities.UserEntity
import com.spydevs.fiestonvirtual.framework.mapper.Mapper

object UserMapper : Mapper<User?, UserEntity>() {

    override fun convertFromInitial(i: User?): UserEntity {
        return UserEntity().apply {
            i?.id?.let {
                id = it
            }
            firstName = i?.name
            lastName = i?.lastName
        }
    }

}