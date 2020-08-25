package com.spydevs.fiestonvirtual.di

import com.spydevs.fiestonvirtual.domain.usecases.abstractions.comment.AddCommentUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.comment.GetCommentsUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.trivia.GetTriviaUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.gallery.GetGalleryUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.gallery.GetPostDetailUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.gallery.UploadImageUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.like.MakeLikeUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.playlist.GetPlayListUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.playlist.RequestSongUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.notifications.SendTokenFirebaseUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.trivia.AnswerTriviaUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.welcome.GetWelcomeUseCase
import com.spydevs.fiestonvirtual.domain.usecases.implementations.welcome.GetWelcomeUseCaseImpl
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.user.GetLocalUserUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.user.LoginUserUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.user.SignOutUseCase
import com.spydevs.fiestonvirtual.domain.usecases.abstractions.user.VerificationSessionUseCase
import com.spydevs.fiestonvirtual.domain.usecases.implementations.comment.AddCommentUseCaseImpl
import com.spydevs.fiestonvirtual.domain.usecases.implementations.comment.GetCommentsUseCaseImpl
import com.spydevs.fiestonvirtual.domain.usecases.implementations.trivia.GetTriviaUseCaseImpl
import com.spydevs.fiestonvirtual.domain.usecases.implementations.gallery.GetGalleryUseCaseImpl
import com.spydevs.fiestonvirtual.domain.usecases.implementations.gallery.GetPostDetailUseCaseImpl
import com.spydevs.fiestonvirtual.domain.usecases.implementations.gallery.UploadImageUseCaseImpl
import com.spydevs.fiestonvirtual.domain.usecases.implementations.like.MakeLikeUseCaseImpl
import com.spydevs.fiestonvirtual.domain.usecases.implementations.playlist.GetPlaylistUseCaseImpl
import com.spydevs.fiestonvirtual.domain.usecases.implementations.playlist.RequestSongUseCaseImpl
import com.spydevs.fiestonvirtual.domain.usecases.implementations.notifications.SendTokenFirebaseUseCaseImpl
import com.spydevs.fiestonvirtual.domain.usecases.implementations.trivia.AnswerTriviaUseCaseImpl
import com.spydevs.fiestonvirtual.domain.usecases.implementations.user.GetLocalUserUseCaseImpl
import com.spydevs.fiestonvirtual.domain.usecases.implementations.user.LoginUserUseCaseImpl
import com.spydevs.fiestonvirtual.domain.usecases.implementations.user.SignOutUseCaseImpl
import com.spydevs.fiestonvirtual.domain.usecases.implementations.user.VerificationSessionUseCaseImpl
import org.koin.dsl.module

val useCasesModule = module {
    single<LoginUserUseCase> {
        LoginUserUseCaseImpl(
            get(),
            get()
        )
    }
    single<GetWelcomeUseCase> {
        GetWelcomeUseCaseImpl(
            get(),
            get()
        )
    }
    single<GetLocalUserUseCase> {
        GetLocalUserUseCaseImpl(get())
    }

    single<GetTriviaUseCase> {
        GetTriviaUseCaseImpl(get(), get())
    }

    single<GetGalleryUseCase> {
        GetGalleryUseCaseImpl(get(), get())
    }

    single<UploadImageUseCase> {
        UploadImageUseCaseImpl(get())
    }

    single<GetCommentsUseCase> {
        GetCommentsUseCaseImpl(get())
    }

    single<GetPostDetailUseCase> {
        GetPostDetailUseCaseImpl(get(), get())
    }

    single<AddCommentUseCase> {
        AddCommentUseCaseImpl(get(), get())
    }

    single<AnswerTriviaUseCase> {
        AnswerTriviaUseCaseImpl(get(), get())
    }

    single<MakeLikeUseCase> {
        MakeLikeUseCaseImpl(get(), get())
    }

    single<SignOutUseCase> {
        SignOutUseCaseImpl(get())
    }

    single<VerificationSessionUseCase> {
        VerificationSessionUseCaseImpl(get())
    }

    single<RequestSongUseCase> {
        RequestSongUseCaseImpl(get(), get())
    }

    single<GetPlayListUseCase> {
        GetPlaylistUseCaseImpl(get(), get())
    }

    single<SendTokenFirebaseUseCase> {
        SendTokenFirebaseUseCaseImpl(get(), get())
    }

}