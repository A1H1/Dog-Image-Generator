package com.dogimagegenerator.app.base

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseMVVMActivity<T : ViewModel, VB : ViewBinding> : BaseActivity<VB>() {
    protected abstract val viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observers()
    }

    abstract fun observers()
    open fun showLoading(isLoading: Boolean) {}
}