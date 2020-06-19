package com.spydevs.fiestonvirtual.di


import com.spydevs.fiestonvirtual.domain.usecases.CodeUseCase
import org.koin.dsl.module

val useCasesModule = module {
//    single { GetFilmsUseCase(get()) }
//    single {  GeocodingUseCase(get())}
    single {  CodeUseCase(get())}
}