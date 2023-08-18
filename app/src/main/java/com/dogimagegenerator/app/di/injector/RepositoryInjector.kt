package com.dogimagegenerator.app.di.injector

import com.dogimagegenerator.app.data.repository.DogImageRepository
import com.dogimagegenerator.app.data.repository.DogImageRepositoryImpl
import org.koin.dsl.module

val repositoryInjector = module {
    single<DogImageRepository> { DogImageRepositoryImpl(get(), get()) }
}