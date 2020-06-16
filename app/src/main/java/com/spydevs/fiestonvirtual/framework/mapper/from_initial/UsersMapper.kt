package com.spydevs.fiestonvirtual.framework.mapper.from_initial

import com.spydevs.fiestonvirtual.domain.models.User
import com.spydevs.fiestonvirtual.framework.database.entities.UserEntity
import com.spydevs.fiestonvirtual.framework.mapper.Mapper

object UsersMapper: Mapper<List<UserEntity>, List<User>>() {
    override fun convertFromInitial(i: List<UserEntity>?): List<User> {
        return i?.map {
            User(it.firstName, it.lastName)
        } ?: listOf()
    }
}