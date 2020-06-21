package com.spydevs.fiestonvirtual.domain.usecases.code

import com.spydevs.fiestonvirtual.domain.models.code.CodeResponse
import com.spydevs.fiestonvirtual.domain.repository.CodeRepository

class VerifyEventCodeUseCaseImpl(private val codeRepository: CodeRepository) :
    VerifyEventCodeUseCase {

    override suspend fun invoke(eventCode: String): CodeResponse {
        return codeRepository.verifyCode(eventCode)
    }

}