package com.spydevs.fiestonvirtual.di

import com.spydevs.fiestonvirtual.data.datasource.CodeDataSource
import com.spydevs.fiestonvirtual.data.datasource.EventDataSource
import com.spydevs.fiestonvirtual.data.datasource.UsersDataSource
import com.spydevs.fiestonvirtual.framework.datasource.CodeDataSourceImpl
import com.spydevs.fiestonvirtual.framework.datasource.EventDataSourceImpl
import com.spydevs.fiestonvirtual.framework.datasource.UsersDataSourceImpl
import org.koin.dsl.module

val dataSourcesModule = module {

    single<UsersDataSource> { UsersDataSourceImpl(get()) }
    single<CodeDataSource> { CodeDataSourceImpl(get()) }
    single<EventDataSource> { EventDataSourceImpl(get()) }

}