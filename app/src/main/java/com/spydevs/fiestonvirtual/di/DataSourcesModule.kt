package com.spydevs.fiestonvirtual.di

import com.spydevs.fiestonvirtual.data.datasource.StarWarsDataSource
import com.spydevs.fiestonvirtual.data.datasource.UsersDataSource
import com.spydevs.fiestonvirtual.data.repository.UsersRepositoryImpl
import com.spydevs.fiestonvirtual.framework.datasource.StarWarsDataSourceImpl
import com.spydevs.fiestonvirtual.framework.datasource.UsersDataSourceImpl
import org.koin.dsl.module

val dataSourcesModule = module {

    single<StarWarsDataSource> {
        StarWarsDataSourceImpl(get())
    }

    single<UsersDataSource> {
        UsersDataSourceImpl(get())
    }

}