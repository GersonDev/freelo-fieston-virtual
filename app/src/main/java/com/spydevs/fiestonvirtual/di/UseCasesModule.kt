package com.spydevs.fiestonvirtual.di

import com.spydevs.fiestonvirtual.domain.usecases.abstractions.code.VerifyEventCodeUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.trivia.GetTriviaUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.gallery.GetGalleryUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.welcome.GetWelcomeUseCase
import com.spydevs.fiestonvirtual.domain.usecases.implementations.welcome.GetWelcomeUseCaseImpl
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.user.GetLocalUserUseCase
import com.spydevs.fiestonvirtual.domain.usecases.implementations.code.VerifyEventCodeUseCaseImpl
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.user.LoginUserUseCase
import com.spydevs.fiestonvirtual.domain.usecases.implementations.trivia.GetTriviaUseCaseImpl
import com.spydevs.fiestonvirtual.domain.usecases.implementations.gallery.GetGalleryUseCaseImpl
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
    single<GetWelcomeUseCase> {
        GetWelcomeUseCaseImpl(
            get()
        )
    }
    single<GetLocalUserUseCase> {
        GetLocalUserUseCaseImpl(get())
    }

    single<GetTriviaUseCase> {
        GetTriviaUseCaseImpl(get())
    }

    single<GetGalleryUseCase> {
        GetGalleryUseCaseImpl(get())
    }
}