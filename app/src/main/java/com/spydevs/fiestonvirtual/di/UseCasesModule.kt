package com.spydevs.fiestonvirtual.di

import com.spydevs.fiestonvirtual.domain.usecases.abstractions.code.VerifyEventCodeUseCase
import com.spydevs.fiestonvirtual.domain.usecases.implementations.codeimpl.VerifyEventCodeUseCaseImpl
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.user.SetLoggedInUserUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.welcome.GetWelcomeUseCase
import com.spydevs.fiestonvirtual.domain.usecases.implementations.userimpl.SetLoggedInUserUseCaseImpl
import com.spydevs.fiestonvirtual.domain.usecases.implementations.welcome.GetWelcomeUseCaseImpl
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
    single<GetWelcomeUseCase> {
        GetWelcomeUseCaseImpl(
            get()
        )
    }
}