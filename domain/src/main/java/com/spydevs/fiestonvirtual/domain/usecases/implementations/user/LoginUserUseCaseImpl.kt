package com.spydevs.fiestonvirtual.domain.usecases.implementations.user

import com.spydevs.fiestonvirtual.domain.models.code.ValidateCodeRequest
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.models.user.GetRemoteUserRequest
import com.spydevs.fiestonvirtual.domain.repository.CodeRepository
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.user.LoginUserUseCase

class LoginUserUseCaseImpl(
    private val codeRepository: CodeRepository,
    private val usersRepository: UsersRepository
) : LoginUserUseCase {

    override suspend operator fun invoke(
        validateCodeRequest: ValidateCodeRequest
    ): ResultType<Boolean, ErrorResponse> {
        return when (val eventCodeResult = codeRepository.verifyCode(validateCodeRequest)) {
            is ResultType.Success -> {
                when (val userResult =
                    usersRepository.getRemoteUser(GetRemoteUserRequest(eventCodeResult.value.idUser))) {
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
