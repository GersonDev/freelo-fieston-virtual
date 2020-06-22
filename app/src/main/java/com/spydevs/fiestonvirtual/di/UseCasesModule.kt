package com.spydevs.fiestonvirtual.di

import com.spydevs.fiestonvirtual.domain.usecases.abstractions.code.VerifyEventCodeUseCase
import com.spydevs.fiestonvirtual.domain.usecases.implementations.codeimpl.VerifyEventCodeUseCaseImpl
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.user.SetLoggedInUserUseCase
import com.spydevs.fiestonvirtual.domain.usecases.implementations.userimpl.SetLoggedInUserUseCaseImpl
import org.koin.dsl.module

val useCasesModule = module {
    single<VerifyEventCodeUseCase> {
        VerifyEventCodeUseCaseImpl(
            get()
        )
    }
    single<SetLoggedInUserUseCase> {
        SetLoggedInUserUseCaseImpl(
            get()
        )
    }
}