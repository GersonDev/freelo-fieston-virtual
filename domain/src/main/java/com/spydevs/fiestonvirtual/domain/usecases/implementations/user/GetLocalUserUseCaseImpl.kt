package com.spydevs.fiestonvirtual.domain.usecases.implementations.user

import com.spydevs.fiestonvirtual.domain.models.user.User
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.user.GetLocalUserUseCase

class GetLocalUserUseCaseImpl(
    private val usersRepository: UsersRepository
) : GetLocalUserUseCase {

    override suspend operator fun invoke(): User {
        return usersRepository.getLocalUser()
    }

}