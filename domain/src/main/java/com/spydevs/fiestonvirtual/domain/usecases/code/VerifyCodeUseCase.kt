package com.spydevs.fiestonvirtual.domain.usecases.code

import com.spydevs.fiestonvirtual.domain.models.code.CodeResponse

interface VerifyCodeUseCase {
    /**
     * This method verifies the user code.
     */
    suspend fun verifyCode(code: String): CodeResponse
}