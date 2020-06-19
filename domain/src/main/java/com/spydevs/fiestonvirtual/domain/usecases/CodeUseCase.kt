package com.spydevs.fiestonvirtual.domain.usecases

import com.spydevs.fiestonvirtual.domain.models.code.CodeResponse
import com.spydevs.fiestonvirtual.domain.repository.CodeRepository

class CodeUseCase(private val codeRepository: CodeRepository) {

    suspend fun verifyCode(code: String): CodeResponse {
        return codeRepository.verifyCode(code)
    }

}