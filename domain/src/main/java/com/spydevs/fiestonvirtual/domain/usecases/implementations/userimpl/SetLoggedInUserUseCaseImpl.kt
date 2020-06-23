package com.spydevs.fiestonvirtual.domain.usecases.implementations.userimpl

import com.spydevs.fiestonvirtual.domain.models.User
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.user.SetLoggedInUserUseCase

class SetLoggedInUserUseCaseImpl(
    private val usersRepository: UsersRepository
) : SetLoggedInUserUseCase {

    override suspend fun invoke(user: User) {
        usersRepository.setLoggedInUser(user)
    }

}
