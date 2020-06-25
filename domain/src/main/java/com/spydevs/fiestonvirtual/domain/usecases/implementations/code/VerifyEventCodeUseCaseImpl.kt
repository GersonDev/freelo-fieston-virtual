package com.spydevs.fiestonvirtual.domain.usecases.implementations.code

import com.spydevs.fiestonvirtual.domain.models.code.EventCode
import com.spydevs.fiestonvirtual.domain.repository.CodeRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.code.VerifyEventCodeUseCase

class VerifyEventCodeUseCaseImpl(
    private val codeRepository: CodeRepository
) : VerifyEventCodeUseCase {

    override suspend operator fun invoke(eventCode: String): ResultType<EventCode, String> {
        return codeRepository.verifyCode(eventCode)
    }

}