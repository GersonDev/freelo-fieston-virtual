package com.spydevs.fiestonvirtual.framework.mapper.implementations

import com.spydevs.fiestonvirtual.data.entities.response.DataUserResponseEntity
import com.spydevs.fiestonvirtual.domain.models.user.User
import com.spydevs.fiestonvirtual.framework.mapper.Mapper

object UserResponseEntityMapper : Mapper<DataUserResponseEntity.UserEntity, User>() {

    override fun convertFromInitial(i: DataUserResponseEntity.UserEntity): User {
        return User(
            id = i.idUser,
            name = i.userName,
            lastName = i.userLastName,
            totalScore = i.userTotalScore,
            ranking = i.userRanking,
            idEvent = i.idEvent
        )
    }

}