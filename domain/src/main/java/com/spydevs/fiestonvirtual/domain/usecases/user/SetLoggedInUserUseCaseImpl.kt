package com.spydevs.fiestonvirtual.domain.usecases.user

import com.spydevs.fiestonvirtual.domain.models.User
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository

class SetLoggedInUserUseCaseImpl(
    private val usersRepository: UsersRepository
) : SetLoggedInUserUseCase {

    override suspend fun invoke(user: User) {
        usersRepository.setLoggedInUser(user)
    }

}
