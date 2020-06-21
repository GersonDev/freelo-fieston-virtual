package com.spydevs.fiestonvirtual.framework.datasource

import com.spydevs.fiestonvirtual.data.datasource.CodeDataSource
import com.spydevs.fiestonvirtual.domain.models.code.CodeResponse
import com.spydevs.fiestonvirtual.framework.api.FiestonVirtualApi
import com.spydevs.fiestonvirtual.framework.mapper.frominitial.CodeResponseMapper

class CodeDataSourceImpl(
    private val fiestonVirtualApi: FiestonVirtualApi
) : CodeDataSource {

    override suspend fun verifyCode(code: String): CodeResponse {
        return CodeResponseMapper.convertFromInitial(fiestonVirtualApi.codeVerify(code))
    }

}