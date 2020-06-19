package com.spydevs.fiestonvirtual.framework.datasource

import com.spydevs.fiestonvirtual.data.datasource.CodeDataSource
import com.spydevs.fiestonvirtual.domain.models.code.CodeResponse
import com.spydevs.fiestonvirtual.framework.api.CodeApi
import com.spydevs.fiestonvirtual.framework.mapper.frominitial.CodeResponseMapper

class CodeDataSourceImpl(
    private val codeApi: CodeApi
) : CodeDataSource {

    override suspend fun verifyCode(code: String): CodeResponse {
        return CodeResponseMapper.convertFromInitial(codeApi.codeVerify(code))
    }

}