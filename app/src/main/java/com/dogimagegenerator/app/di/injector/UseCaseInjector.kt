package com.dogimagegenerator.app.di.injector

import com.dogimagegenerator.app.data.usecase.DogImageUseCase
import com.dogimagegenerator.app.data.usecase.DogImageUseCaseImpl
import org.koin.dsl.module

val userCaseInjector = module {
    single<DogImageUseCase> { DogImageUseCaseImpl(get()) }
}