package com.spydevs.fiestonvirtual.di

import com.spydevs.fiestonvirtual.data.datasource.CodeDataSource
import com.spydevs.fiestonvirtual.data.datasource.EventDataSource
import com.spydevs.fiestonvirtual.data.datasource.TriviaDataSource
import com.spydevs.fiestonvirtual.data.datasource.GalleryDataSource
import com.spydevs.fiestonvirtual.data.datasource.UsersDataSource
import com.spydevs.fiestonvirtual.data.datasource.CommentDataSource
import com.spydevs.fiestonvirtual.framework.datasource.CodeDataSourceImpl
import com.spydevs.fiestonvirtual.framework.datasource.EventDataSourceImpl
import com.spydevs.fiestonvirtual.framework.datasource.TriviaDataSourceImpl
import com.spydevs.fiestonvirtual.framework.datasource.GalleryDataSourceImpl
import com.spydevs.fiestonvirtual.framework.datasource.UsersDataSourceImpl
import com.spydevs.fiestonvirtual.framework.datasource.CommentDataSourceImpl
import org.koin.dsl.module

val dataSourcesModule = module {

    single<UsersDataSource> { UsersDataSourceImpl(get(), get()) }
    single<CodeDataSource> { CodeDataSourceImpl(get()) }
    single<EventDataSource> { EventDataSourceImpl(get()) }
    single<TriviaDataSource> { TriviaDataSourceImpl(get()) }
    single<GalleryDataSource> { GalleryDataSourceImpl(get()) }
    single<CommentDataSource> { CommentDataSourceImpl(get()) }

}