package com.example.joshtalk.di

import com.example.joshtalk.di.appModule
import org.koin.core.context.startKoin

fun doInitKoin() {
    startKoin {
        modules(appModule)
    }
}