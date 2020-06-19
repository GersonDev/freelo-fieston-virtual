package com.spydevs.fiestonvirtual.framework.api

import com.spydevs.fiestonvirtual.framework.response.code.CodeResponseEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface CodeApi {

    @GET("consulta_codigo.php")
    suspend fun codeVerify(@Query("codInv") codInv: String): CodeResponseEntity

}