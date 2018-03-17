package com.omertex.omertextest.di

import android.app.Application

class AppInjector : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(applicationContext))
                .build()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }

}