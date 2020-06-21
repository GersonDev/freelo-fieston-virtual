package com.spydevs.fiestonvirtual.data.repository

import com.spydevs.fiestonvirtual.data.datasource.CodeDataSource
import com.spydevs.fiestonvirtual.domain.models.code.CodeResponse
import com.spydevs.fiestonvirtual.domain.repository.CodeRepository

class CodeRepositoryImpl(
    private val codeDataSource: CodeDataSource
) : CodeRepository {

    override suspend fun verifyCode(eventCode: String): CodeResponse {
        return codeDataSource.verifyCode(eventCode)
    }

}