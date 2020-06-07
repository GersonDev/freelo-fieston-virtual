package com.spydevs.fiestonvirtual.di

import com.spydevs.fiestonvirtual.ui.main.home.HomeViewModel
import com.spydevs.fiestonvirtual.ui.main.cart.CartViewModel

import com.spydevs.fiestonvirtual.ui.main.user.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel { HomeViewModel() }
    viewModel { CartViewModel() }
    viewModel { UserViewModel() }

}