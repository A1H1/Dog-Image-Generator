package com.dogimagegenerator.app.di.injector

import com.dogimagegenerator.app.data.local.PrefService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataInjector = module {
    single { PrefService(androidContext()) }
}