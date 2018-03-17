package com.omertex.omertextest.di

import android.content.Context
import com.omertex.omertextest.data.repository.DataRepository
import com.omertex.omertextest.data.repository.DataRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val applicationContext: Context) {

    @Provides
    fun provideContext(): Context = applicationContext

    @Provides
    @Singleton
    fun provideDataRepository(): DataRepository {
        return DataRepositoryImpl()
    }
}