package com.dogimagegenerator.app.di.injector

import com.dogimagegenerator.app.ui.generate.GenerateViewModel
import com.dogimagegenerator.app.ui.history.HistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelInjector = module {
    viewModel { GenerateViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
}