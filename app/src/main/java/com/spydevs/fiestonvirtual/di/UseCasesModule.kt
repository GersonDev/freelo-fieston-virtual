package com.spydevs.fiestonvirtual.di

import com.spydevs.fiestonvirtual.domain.usecases.code.VerifyEventCodeUseCase
import com.spydevs.fiestonvirtual.domain.usecases.code.VerifyEventCodeUseCaseImpl
import com.spydevs.fiestonvirtual.domain.usecases.user.SetLoggedInUserUseCase
import com.spydevs.fiestonvirtual.domain.usecases.user.SetLoggedInUserUseCaseImpl
import org.koin.dsl.module

val useCasesModule = module {
    single<VerifyEventCodeUseCase> { VerifyEventCodeUseCaseImpl(get()) }
    single<SetLoggedInUserUseCase> { SetLoggedInUserUseCaseImpl(get()) }
}