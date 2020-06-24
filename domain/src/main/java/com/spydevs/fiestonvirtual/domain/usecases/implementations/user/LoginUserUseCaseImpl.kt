package com.spydevs.fiestonvirtual.domain.usecases.implementations.user

import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.code.VerifyEventCodeUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.user.LoginUserUseCase

class LoginUserUseCaseImpl(
    private val verifyEventCodeUseCase: VerifyEventCodeUseCase,
    private val usersRepository: UsersRepository
) : LoginUserUseCase {

    override suspend operator fun invoke(eventCode: String): ResultType<Boolean, String> {
        return when (val eventCodeResult = verifyEventCodeUseCase(eventCode)) {
            is ResultType.Success -> {
                when (val userResult =
                    usersRepository.getRemoteUser(eventCodeResult.value.userId ?: "")) {
                    is ResultType.Success -> {
                        usersRepository.setLoggedInUser(userResult.value)
                        ResultType.Success(true)
                    }
                    is ResultType.Error -> {
                        ResultType.Error(userResult.value)
                    }
                }
            }
            is ResultType.Error -> {
                ResultType.Error(eventCodeResult.value)
            }
        }
    }

}
