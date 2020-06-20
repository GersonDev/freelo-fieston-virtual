package com.spydevs.fiestonvirtual.di

import com.google.gson.GsonBuilder
import com.spydevs.fiestonvirtual.framework.api.CodeApi
import com.spydevs.fiestonvirtual.framework.api.StarWarsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofitApiModule = module {

    single(named("HttpLoggingInterceptor")) {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    single {
        OkHttpClient()
            .newBuilder()
            .addInterceptor(get(named("HttpLoggingInterceptor")))
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        GsonBuilder()
            .setLenient()
            .create()
    }

    single {
        Retrofit.Builder()
            .baseUrl("http://swapi.dev/api/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
            .create(StarWarsApi::class.java)
    }

    single {
        Retrofit.Builder()
            .baseUrl("http://arpanetapp.com/fieston-virtual/api/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
            .create(CodeApi::class.java)
    }

}