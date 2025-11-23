package com.example.joshtalk.android

import android.app.Application
import com.example.joshtalk.AndroidContext
import com.example.joshtalk.di.initKoin
import org.koin.android.ext.koin.androidContext


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        AndroidContext.applicationContext = this

        initKoin {
            androidContext(this@MyApplication)
        }
    }
}