package com.spydevs.fiestonvirtual.domain.usecases.implementations.user

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.user.SignOutRequest
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.user.SignOutUseCase

class SignOutUseCaseImpl(
    private val usersRepository: UsersRepository
) : SignOutUseCase {

    override suspend fun invoke(): ResultType<Boolean, ErrorResponse> {
        val user = usersRepository.getLocalUser()
        return when (
            val eventCodeResult = usersRepository.signOut(
                SignOutRequest(user.id)
            )) {
            is ResultType.Success -> {
                usersRepository.deleteLocalAllUsers()
                ResultType.Success(true)
            }
            is ResultType.Error -> {
                ResultType.Error(eventCodeResult.value)
            }
        }
    }

}