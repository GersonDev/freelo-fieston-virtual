package com.spydevs.fiestonvirtual.di

import com.spydevs.fiestonvirtual.domain.usecases.code.VerifyCodeUseCase
import com.spydevs.fiestonvirtual.domain.usecases.code.VerifyCodeUseCaseImpl
import com.spydevs.fiestonvirtual.domain.usecases.user.UserUseCase
import org.koin.dsl.module

val useCasesModule = module {
//    single { GetFilmsUseCase(get()) }
//    single {  GeocodingUseCase(get())}
    single<VerifyCodeUseCase> { VerifyCodeUseCaseImpl(get()) }
    single { UserUseCase(get()) }
}