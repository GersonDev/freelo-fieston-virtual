package com.spydevs.fiestonvirtual.framework.mapper.frominitial

import com.spydevs.fiestonvirtual.domain.models.code.CodeResponse
import com.spydevs.fiestonvirtual.framework.mapper.Mapper
import com.spydevs.fiestonvirtual.framework.response.code.CodeResponseEntity

object CodeResponseMapper : Mapper<CodeResponseEntity, CodeResponse>() {

    override fun convertFromInitial(i: CodeResponseEntity?): CodeResponse {
        val codeResponseItemList = i?.map {
            CodeResponseItemMapper.convertFromInitial(it)
        } as List
        return CodeResponse().apply {
            addAll(codeResponseItemList)
        }
    }

}