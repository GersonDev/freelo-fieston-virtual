package com.spydevs.fiestonvirtual.domain.usecases.implementations.user

import com.spydevs.fiestonvirtual.domain.models.user.User
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.user.GetUserUseCase

//TODO implementation use case.
class GetUserUseCaseImpl : GetUserUseCase {

    override suspend operator fun invoke(): User {
        return User()
    }

}