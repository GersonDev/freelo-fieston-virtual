package com.spydevs.fiestonvirtual.framework.mapper.implementations

import com.spydevs.fiestonvirtual.domain.models.user.User
import com.spydevs.fiestonvirtual.framework.database.entities.UserEntity
import com.spydevs.fiestonvirtual.framework.mapper.Mapper

object UserEntityMapper : Mapper<UserEntity, User>() {

    override fun convertFromInitial(i: UserEntity): User {
        return User(
            id = i.id,
            name = i.firstName,
            lastName = i.lastName,
            totalScore = i.totalScore,
            ranking = i.ranking,
            idEvent = i.idEvent,
            avatar = i.avatar,
            token = i.token
        )
    }

}