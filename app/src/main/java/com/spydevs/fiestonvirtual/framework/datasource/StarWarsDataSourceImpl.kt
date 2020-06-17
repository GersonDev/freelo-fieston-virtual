package com.spydevs.fiestonvirtual.framework.datasource

import android.util.Log
import com.spydevs.fiestonvirtual.data.datasource.StarWarsDataSource
import com.spydevs.fiestonvirtual.domain.models.StarWars
import com.spydevs.fiestonvirtual.framework.api.StarWarsApi
import com.spydevs.fiestonvirtual.framework.mapper.frominitial.StarWarsMapper
import com.spydevs.fiestonvirtual.framework.response.StarWarsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StarWarsDataSourceImpl(private val starWarsApi: StarWarsApi) :
    StarWarsDataSource {

    //TODO Implement RESULT class in a near future
    override suspend fun getFilms(): StarWars {
        val call = starWarsApi.getJenkinsUserData()

        var starWars = StarWars()

        call.enqueue(object : Callback<StarWarsResponse> {
            override fun onFailure(call: Call<StarWarsResponse>, t: Throwable) {
                val throwable = Throwable("Error: ${t.message ?: "Unknown error"}")
                Log.e("ERROR", throwable.message)
            }

            override fun onResponse(
                call: Call<StarWarsResponse>,
                response: Response<StarWarsResponse>
            ) {
                if (response.isSuccessful) {
                    starWars = StarWarsMapper.convertFromInitial(response.body())
                } else {
                    Log.e("ERROR", "Error: ${response.code()} ${response.message()}")
                }
            }
        })
        return starWars
    }

}