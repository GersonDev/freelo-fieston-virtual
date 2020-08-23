package com.spydevs.fiestonvirtual.application

import android.app.Application
import com.spydevs.fiestonvirtual.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FiestonVirtualApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@FiestonVirtualApplication)
            modules(
                listOf(
                    socketsApiModule,
                    retrofitApiModule,
                    databaseModule,
                    dataSourcesModule,
                    repositoryModule,
                    useCasesModule,
                    viewModelsModule,
                    formatterModule
                )
            )
        }
    }
}