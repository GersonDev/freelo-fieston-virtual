package com.spydevs.fiestonvirtual.domain.usecases.implementations.user

import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.user.VerificationSessionUseCase

class VerificationSessionUseCaseImpl(
    private val usersRepository: UsersRepository
) : VerificationSessionUseCase {

    override suspend fun invoke(): ResultType<Boolean, ErrorResponse> {
        return if (usersRepository.getLocalUsers().isNotEmpty()) {
            ResultType.Success(true)
        } else {
            ResultType.Success(false)
        }
    }

}