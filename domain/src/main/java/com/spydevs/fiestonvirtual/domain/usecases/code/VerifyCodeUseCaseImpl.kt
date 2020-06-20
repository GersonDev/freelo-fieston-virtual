package com.spydevs.fiestonvirtual.domain.usecases.code

import com.spydevs.fiestonvirtual.domain.models.code.CodeResponse
import com.spydevs.fiestonvirtual.domain.repository.CodeRepository

class VerifyCodeUseCaseImpl(private val codeRepository: CodeRepository) : VerifyCodeUseCase {

    override suspend fun verifyCode(code: String): CodeResponse {
        return codeRepository.verifyCode(code)
    }

}