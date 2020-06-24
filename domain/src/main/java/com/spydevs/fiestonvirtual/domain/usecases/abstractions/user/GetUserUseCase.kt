package com.spydevs.fiestonvirtual.domain.usecases.abstractions.user

import com.spydevs.fiestonvirtual.domain.models.user.User

//TODO implementation use case.
interface GetUserUseCase {

    suspend operator fun invoke(): User
}