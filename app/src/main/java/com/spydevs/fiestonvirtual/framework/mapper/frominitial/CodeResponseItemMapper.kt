package com.spydevs.fiestonvirtual.framework.mapper.frominitial

import com.spydevs.fiestonvirtual.domain.models.code.CodeResponseItem
import com.spydevs.fiestonvirtual.framework.mapper.Mapper
import com.spydevs.fiestonvirtual.data.entities.response.CodeItemResponseEntity

object CodeResponseItemMapper : Mapper<CodeItemResponseEntity, CodeResponseItem>() {
    override fun convertFromInitial(i: CodeItemResponseEntity?): CodeResponseItem {
        return CodeResponseItem().apply {
            apeMat = i?.apeMat
            apePat = i?.apePat
            celular = i?.celular
            codInv = i?.codInv
            email = i?.email
            estado = i?.estado
            likeFoto = i?.likeFoto
            likeVideo= i?.likeVideo
            mensaje= i?.mensaje
            nomUs= i?.nomUs
            puntajeTotal= i?.puntajeTotal
            telef= i?.telef
        }
    }
}