package com.spydevs.fiestonvirtual.di

import com.github.nkzawa.socketio.client.IO
import org.koin.dsl.module
import java.net.URISyntaxException

val socketsApiModule = module {

    single {
        try {
            IO.socket("http://192.168.0.103:3000/")
        } catch (e: URISyntaxException) {
            throw  RuntimeException(e)
        }
    }

}