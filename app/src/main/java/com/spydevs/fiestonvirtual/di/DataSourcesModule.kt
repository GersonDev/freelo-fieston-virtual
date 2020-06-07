package com.spydevs.fiestonvirtual.di

import com.spydevs.fiestonvirtual.data.datasource.StarWarsDataSource
import com.spydevs.fiestonvirtual.framework.datasource.StarWarsDataSourceImpl
import org.koin.dsl.module

val dataSourcesModule = module {

    single<StarWarsDataSource> {
        StarWarsDataSourceImpl(get())
    }

}