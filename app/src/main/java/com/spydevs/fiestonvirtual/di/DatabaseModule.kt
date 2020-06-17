package com.spydevs.fiestonvirtual.di

import com.spydevs.fiestonvirtual.framework.database.FiestonVirtualDatabase
import org.koin.dsl.module

val databaseModule = module {

    single {
        FiestonVirtualDatabase.buildDatabase(get())
    }

    single { get<FiestonVirtualDatabase>().usersDao() }

}