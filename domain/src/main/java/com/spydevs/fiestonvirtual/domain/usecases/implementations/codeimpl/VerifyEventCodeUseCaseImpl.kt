package com.spydevs.fiestonvirtual.domain.usecases.implementations.codeimpl

import com.spydevs.fiestonvirtual.domain.models.code.CodeResponse
import com.spydevs.fiestonvirtual.domain.repository.CodeRepository
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.code.VerifyEventCodeUseCase

class VerifyEventCodeUseCaseImpl(private val codeRepository: CodeRepository) :
    VerifyEventCodeUseCase {

    override suspend fun invoke(eventCode: String): CodeResponse {
        return codeRepository.verifyCode(eventCode)
    }

}