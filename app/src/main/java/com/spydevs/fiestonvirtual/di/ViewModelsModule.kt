package com.spydevs.fiestonvirtual.di

import com.spydevs.fiestonvirtual.ui.main.home.HomeViewModel
import com.spydevs.fiestonvirtual.ui.main.photo.PhotoViewModel

import com.spydevs.fiestonvirtual.ui.main.chat.GalleryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel { HomeViewModel(get()) }
    viewModel { PhotoViewModel() }
    viewModel { GalleryViewModel() }

}