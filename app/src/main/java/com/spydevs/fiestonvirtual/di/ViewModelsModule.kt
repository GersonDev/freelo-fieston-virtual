package com.spydevs.fiestonvirtual.di

import com.spydevs.fiestonvirtual.ui.codeverification.CodeVerificationViewModel
import com.spydevs.fiestonvirtual.ui.main.MainViewModel
import com.spydevs.fiestonvirtual.ui.main.home.HomeViewModel
import com.spydevs.fiestonvirtual.ui.main.photo.PhotoViewModel
import com.spydevs.fiestonvirtual.ui.main.gallery.GalleryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel { HomeViewModel(get()) }
    viewModel { PhotoViewModel() }
    viewModel { GalleryViewModel() }
    viewModel { MainViewModel(get()) }
    viewModel { CodeVerificationViewModel(get()) }

}