package com.spydevs.fiestonvirtual.di

import com.spydevs.fiestonvirtual.data.datasource.*
import com.spydevs.fiestonvirtual.framework.datasource.*
import org.koin.dsl.module

val dataSourcesModule = module {

    single<UsersDataSource> { UsersDataSourceImpl(get(), get()) }
    single<CodeDataSource> { CodeDataSourceImpl(get()) }
    single<EventDataSource> { EventDataSourceImpl(get()) }
    single<TriviaDataSource> { TriviaDataSourceImpl(get()) }
    single<GalleryDataSource> { GalleryDataSourceImpl(get()) }
    single<CommentDataSource> { CommentDataSourceImpl(get()) }
    single<LikeDataSource> { LikeDataSourceImpl(get()) }
    single<ChatMessageDataSource> { ChatMessageDataSourceImpl(get()) }
    single<PlayListDataSource> { PlayListDataSourceImpl(get()) }
    single<FcmDataSource> { FcmDataSourceImpl(get()) }

}