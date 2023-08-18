package com.dogimagegenerator.app

import android.app.Application
import com.dogimagegenerator.app.di.injector.dataInjector
import com.dogimagegenerator.app.di.injector.networkInjector
import com.dogimagegenerator.app.di.injector.repositoryInjector
import com.dogimagegenerator.app.di.injector.userCaseInjector
import com.dogimagegenerator.app.di.injector.viewModelInjector
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                dataInjector,
                repositoryInjector,
                userCaseInjector,
                viewModelInjector,
                networkInjector
            )
        }
    }
}