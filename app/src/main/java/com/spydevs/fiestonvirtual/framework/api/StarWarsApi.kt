package com.spydevs.fiestonvirtual.framework.api

import com.spydevs.fiestonvirtual.framework.response.StarWarsResponse
import retrofit2.Call
import retrofit2.http.GET

interface StarWarsApi {

    @GET("people/1")
    suspend fun getJenkinsUserData(): Call<StarWarsResponse>

}