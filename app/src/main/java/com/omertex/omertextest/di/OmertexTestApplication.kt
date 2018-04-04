package com.omertex.omertextest.di

import android.app.Application
import com.omertex.omertextest.data.repository.DataRepository
import com.omertex.omertextest.data.repository.DataRepositoryImpl
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.applicationContext

class OmertexTestApplication : Application() {

    private val omertexTestModules = applicationContext {
        bean { DataRepositoryImpl() as DataRepository }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(omertexTestModules))
    }

}