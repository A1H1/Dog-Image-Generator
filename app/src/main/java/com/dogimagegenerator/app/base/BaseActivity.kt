package com.dogimagegenerator.app.base

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.dogimagegenerator.app.R
import com.dogimagegenerator.app.data.error.ErrorDTO
import com.dogimagegenerator.app.data.error.ErrorResponse
import com.dogimagegenerator.app.ui.error.ErrorBottomSheet

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity(), BaseErrorInterface,
    ErrorBottomSheet.ErrorInterface {

    protected abstract fun inflateViewBinding(): T
    protected abstract fun init()
    private lateinit var _binding: T
    protected val binding get() = _binding
    private var errorBottomSheet: ErrorBottomSheet? = null
    protected open fun isBackButtonEnabled() = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflateViewBinding()
        setContentView(_binding.root)
        if (isBackButtonEnabled()) supportActionBar?.setDisplayHomeAsUpEnabled(true)
        init()
    }

    override fun showInternetError() {
        errorBottomSheet?.dismiss()
        errorBottomSheet = ErrorBottomSheet.createInstance(
            ErrorDTO(
                title = getString(R.string.oops_no_internet_connectivity),
                subTitle = getString(R.string.no_internet_message),
                icon = R.drawable.ic_no_wifi
            )
        ).apply {
            setErrorInterface(this@BaseActivity)
            show(supportFragmentManager)
        }
    }

    override fun showSocketTimeoutError() {
        errorBottomSheet?.dismiss()
        errorBottomSheet = ErrorBottomSheet.createInstance(
            ErrorDTO(
                title = getString(R.string.server_timeout),
                subTitle = getString(R.string.please_try_again_later)
            )
        ).apply {
            setErrorInterface(this@BaseActivity)
            show(supportFragmentManager)
        }
    }

    override fun onUnauthorizedAccess(errorResponse: ErrorResponse) {
        super.onUnauthorizedAccess(errorResponse)
        finish()
    }

    override fun showOtherError(errorResponse: ErrorResponse) {
        errorBottomSheet?.dismiss()
        errorBottomSheet = ErrorBottomSheet.createInstance(
            ErrorDTO(
                title = getString(R.string.oops),
                subTitle = errorResponse.message ?: getString(R.string.something_went_wrong)
            )
        ).apply {
            setErrorInterface(this@BaseActivity)
            show(supportFragmentManager)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (isBackButtonEnabled() && item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}