package com.spydevs.fiestonvirtual.di

import com.spydevs.fiestonvirtual.domain.usecases.code.VerifyCodeUseCase
import com.spydevs.fiestonvirtual.domain.usecases.code.VerifyCodeUseCaseImpl
import com.spydevs.fiestonvirtual.domain.usecases.user.SetLoggedInUserUseCase
import com.spydevs.fiestonvirtual.domain.usecases.user.SetLoggedInUserUseCaseImpl
import org.koin.dsl.module

val useCasesModule = module {
//    single { GetFilmsUseCase(get()) }
//    single {  GeocodingUseCase(get())}
    single<VerifyCodeUseCase> { VerifyCodeUseCaseImpl(get()) }
    single<SetLoggedInUserUseCase> { SetLoggedInUserUseCaseImpl(get()) }
}