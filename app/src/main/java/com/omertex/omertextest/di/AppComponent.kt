package com.omertex.omertextest.di

import com.omertex.omertextest.ui.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Component(modules = [(AppModule::class)])
@Singleton
interface AppComponent {

    fun inject(mainPresenter: MainPresenter)

}