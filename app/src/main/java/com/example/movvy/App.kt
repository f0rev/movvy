package com.example.movvy

import android.app.Application
import android.content.Context
import com.example.movvy.di.module.ApplicationComponent
import com.example.movvy.di.module.DaggerApplicationComponent


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        ApplicationComponent.init(
            DaggerApplicationComponent.builder()
                .build()
        )
        ApplicationComponent.get().inject(this)
    }

    companion object {
        @Volatile
        lateinit var appContext: Context
            private set
    }
}