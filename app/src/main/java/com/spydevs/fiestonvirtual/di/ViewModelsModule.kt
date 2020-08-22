package com.spydevs.fiestonvirtual.di

import com.spydevs.fiestonvirtual.ui.codeverification.CodeVerificationViewModel
import com.spydevs.fiestonvirtual.ui.gallerydetail.GalleryDetailViewModel
import com.spydevs.fiestonvirtual.ui.main.MainViewModel
import com.spydevs.fiestonvirtual.ui.main.home.HomeViewModel
import com.spydevs.fiestonvirtual.ui.main.photo.PhotoViewModel
import com.spydevs.fiestonvirtual.ui.main.gallery.GalleryViewModel
import com.spydevs.fiestonvirtual.ui.main.trivia.TriviaViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel { HomeViewModel(get()) }
    viewModel { PhotoViewModel() }
    viewModel { GalleryViewModel(get()) }
    viewModel { MainViewModel(get(), get()) }
    viewModel { CodeVerificationViewModel(get()) }
    viewModel { TriviaViewModel(get(), get()) }
    viewModel { GalleryDetailViewModel(get(), get(), get(), get()) }

}