package com.spydevs.fiestonvirtual.di

import com.spydevs.fiestonvirtual.data.repository.CodeRepositoryImpl
import com.spydevs.fiestonvirtual.data.repository.UsersRepositoryImpl
import com.spydevs.fiestonvirtual.domain.repository.CodeRepository
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UsersRepository> { UsersRepositoryImpl(get()) }
    single<CodeRepository> { CodeRepositoryImpl(get()) }

}
