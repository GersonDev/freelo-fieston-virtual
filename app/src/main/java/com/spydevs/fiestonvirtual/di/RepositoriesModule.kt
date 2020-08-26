package com.spydevs.fiestonvirtual.di

import com.spydevs.fiestonvirtual.data.repository.*
import com.spydevs.fiestonvirtual.domain.repository.*
import org.koin.dsl.module

val repositoryModule = module {
    single<UsersRepository> { UsersRepositoryImpl(get()) }
    single<CodeRepository> { CodeRepositoryImpl(get()) }
    single<EventRepository> { EventRepositoryImpl(get()) }
    single<TriviaRepository> { TriviaRepositoryImpl(get()) }
    single<GalleryRepository> { GalleryRepositoryImpl(get()) }
    single<CommentRepository> { CommentRepositoryImpl(get()) }
    single<LikeRepository> { LikeRepositoryImpl(get()) }
    single<PlaylistRepository> { PlaylistRepositoryImpl(get()) }
    single<FcmRepository> { FcmRepositoryImpl(get()) }
    single<RankingRepository> { RankingRepositoryImpl(get()) }
}
