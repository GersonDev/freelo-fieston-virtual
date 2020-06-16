package com.spydevs.fiestonvirtual.di

import com.spydevs.fiestonvirtual.data.repository.StarWarsRepositoryImpl
import com.spydevs.fiestonvirtual.data.repository.UsersRepositoryImpl
import com.spydevs.fiestonvirtual.domain.repository.StarWarsRepository
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<StarWarsRepository> { StarWarsRepositoryImpl(get()) }
    single<UsersRepository> { UsersRepositoryImpl(get()) }
}
