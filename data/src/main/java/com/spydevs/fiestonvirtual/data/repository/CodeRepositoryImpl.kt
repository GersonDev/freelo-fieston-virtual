package com.spydevs.fiestonvirtual.data.repository

import com.spydevs.fiestonvirtual.data.datasource.CodeDataSource
import com.spydevs.fiestonvirtual.domain.models.code.EventCode
import com.spydevs.fiestonvirtual.domain.models.code.ValidateCodeRequest
import com.spydevs.fiestonvirtual.domain.models.error.ErrorResponse
import com.spydevs.fiestonvirtual.domain.repository.CodeRepository
import com.spydevs.fiestonvirtual.domain.resource.ResultType

class CodeRepositoryImpl(
    private val codeDataSource: CodeDataSource
) : CodeRepository {

    override suspend fun verifyCode(
        validateCodeRequest:
        ValidateCodeRequest
    ): ResultType<EventCode, ErrorResponse> {
        return codeDataSource.verifyCode(validateCodeRequest)
    }

}