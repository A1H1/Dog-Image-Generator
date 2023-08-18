package com.dogimagegenerator.app.di.injector

import com.dogimagegenerator.app.di.utils.provideMoshi
import com.dogimagegenerator.app.network.NetworkModule.provideClient
import com.dogimagegenerator.app.network.NetworkModule.provideDogAPIService
import com.dogimagegenerator.app.network.NetworkModule.provideMoshiFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkInjector = module {
    single { provideMoshiFactory() }
    single { provideClient(get(), androidContext()) }
    single { provideDogAPIService(get()) }
    single { provideMoshi() }
}