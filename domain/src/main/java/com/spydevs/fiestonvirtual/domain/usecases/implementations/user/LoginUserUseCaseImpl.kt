package com.spydevs.fiestonvirtual.domain.usecases.implementations.user

import com.spydevs.fiestonvirtual.domain.repository.CodeRepository
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.user.LoginUserUseCase

class LoginUserUseCaseImpl(
    private val codeRepository: CodeRepository,
    private val usersRepository: UsersRepository
) : LoginUserUseCase {

    override suspend operator fun invoke(eventCode: String): ResultType<Boolean, String> {
        return when (val eventCodeResult = codeRepository.verifyCode(eventCode)) {
            is ResultType.Success -> {
                when (val userResult =
                    usersRepository.getRemoteUser(eventCodeResult.value.userId ?: 0)) {
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
