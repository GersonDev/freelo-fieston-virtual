package com.spydevs.fiestonvirtual.di

import com.spydevs.fiestonvirtual.domain.usecases.abstractions.code.VerifyEventCodeUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.user.GetLocalUserUseCase
import com.spydevs.fiestonvirtual.domain.usecases.implementations.code.VerifyEventCodeUseCaseImpl
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.user.LoginUserUseCase
import com.spydevs.fiestonvirtual.domain.usecases.implementations.user.GetLocalUserUseCaseImpl
import com.spydevs.fiestonvirtual.domain.usecases.implementations.user.LoginUserUseCaseImpl
import org.koin.dsl.module

val useCasesModule = module {
    single<VerifyEventCodeUseCase> {
        VerifyEventCodeUseCaseImpl(
            get()
        )
    }
    single<LoginUserUseCase> {
        LoginUserUseCaseImpl(
            get(),
            get()
        )
    }
    single<GetLocalUserUseCase> {
        GetLocalUserUseCaseImpl(get())
    }
}