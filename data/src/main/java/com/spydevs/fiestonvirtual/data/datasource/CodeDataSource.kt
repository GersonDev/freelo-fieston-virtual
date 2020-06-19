package com.spydevs.fiestonvirtual.data.datasource

import com.spydevs.fiestonvirtual.domain.models.code.CodeResponse

interface CodeDataSource {

    suspend fun verifyCode(code: String): CodeResponse

}