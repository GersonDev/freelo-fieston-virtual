package com.spydevs.fiestonvirtual.di

import com.spydevs.fiestonvirtual.data.repository.StarWarsRepositoryImpl
import com.spydevs.fiestonvirtual.domain.repository.StarWarsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<StarWarsRepository> { StarWarsRepositoryImpl(get()) }
}
