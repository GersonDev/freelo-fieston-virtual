package com.spydevs.fiestonvirtual.di

import com.spydevs.fiestonvirtual.data.repository.CodeRepositoryImpl
import com.spydevs.fiestonvirtual.data.repository.EventRepositoryImpl
import com.spydevs.fiestonvirtual.data.repository.GalleryRepositoryImpl
import com.spydevs.fiestonvirtual.data.repository.UsersRepositoryImpl
import com.spydevs.fiestonvirtual.domain.repository.CodeRepository
import com.spydevs.fiestonvirtual.domain.repository.EventRepository
import com.spydevs.fiestonvirtual.domain.repository.GalleryRepository
import com.spydevs.fiestonvirtual.domain.repository.UsersRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UsersRepository> { UsersRepositoryImpl(get()) }
    single<CodeRepository> { CodeRepositoryImpl(get()) }
    single<EventRepository> { EventRepositoryImpl(get()) }
    single<GalleryRepository> { GalleryRepositoryImpl(get()) }
}
