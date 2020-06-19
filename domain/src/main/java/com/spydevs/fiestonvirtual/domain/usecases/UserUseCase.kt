package com.spydevs.fiestonvirtual.domain.usecases

import com.spydevs.fiestonvirtual.domain.models.User
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository

class UserUseCase(
    private val usersRepository: UsersRepository
) {
    suspend fun insertUser(user: User) {
        usersRepository.insertUser(user)
    }

}
