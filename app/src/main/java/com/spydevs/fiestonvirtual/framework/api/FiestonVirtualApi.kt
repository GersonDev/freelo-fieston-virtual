package com.spydevs.fiestonvirtual.framework.api

import com.spydevs.fiestonvirtual.framework.response.code.CodeResponseEntity
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This API contents all end point of the services.
 */
interface FiestonVirtualApi {

    /**
     * This [codeEvent] is event code to input to application.
     * @return all data logged user.
     */
    @GET("consulta_codigo.php")
    suspend fun codeVerify(@Query("codInv") codeEvent: String): CodeResponseEntity

}