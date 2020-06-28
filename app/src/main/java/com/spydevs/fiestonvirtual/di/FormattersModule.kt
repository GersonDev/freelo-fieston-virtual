package com.spydevs.fiestonvirtual.di

import com.spydevs.fiestonvirtual.util.formatter.Base64ImageFormattingStrategy
import com.spydevs.fiestonvirtual.util.formatter.ImageFormattingStrategy
import org.koin.dsl.module

val formatterModule = module {
    single<ImageFormattingStrategy> {
        Base64ImageFormattingStrategy()
    }
}