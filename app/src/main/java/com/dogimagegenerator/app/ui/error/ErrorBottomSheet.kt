package com.dogimagegenerator.app.ui.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.dogimagegenerator.app.base.BaseBottomSheet
import com.dogimagegenerator.app.data.error.ErrorDTO
import com.dogimagegenerator.app.databinding.BottomSheetErrorBinding
import com.dogimagegenerator.app.utils.setDebounceClickListener
import com.squareup.moshi.Moshi

class ErrorBottomSheet : BaseBottomSheet<BottomSheetErrorBinding>() {
    interface ErrorInterface {
        fun onRetry() {}
        fun onHideError() {}
    }

    companion object {
        private const val ERROR = "ERROR"

        fun createInstance(error: ErrorDTO): ErrorBottomSheet {
            val errorBottomSheet = ErrorBottomSheet()
            val bundle = Bundle()
            val errorAdapter = Moshi.Builder().build().adapter(ErrorDTO::class.java)
            bundle.putString(ERROR, errorAdapter.toJson(error))
            errorBottomSheet.arguments = bundle

            return errorBottomSheet
        }
    }

    private var errorInterface: ErrorInterface? = null
    private var actionFunction: (() -> Unit)? = null

    override fun tag() = "ERROR_BOTTOM_SHEET"
    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = BottomSheetErrorBinding.inflate(inflater, container, false)

    override fun init() {
        isCancelable = false

        val errorAdapter = Moshi.Builder().build().adapter(ErrorDTO::class.java)
        with(errorAdapter.fromJson(requireArguments().getString(ERROR)!!)!!) {
            binding.errorTitleTextView.text = title
            binding.errorSubtitleTextView.isVisible = subTitle.isNotEmpty()
            binding.errorSubtitleTextView.text = subTitle
            if (primaryButtonText.isNotEmpty())
                binding.retryButton.text = primaryButtonText
            binding.errorImageView.setImageResource(icon)
        }
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.retryButton.setDebounceClickListener(action = ::onRetry)
        binding.goBackButton.setDebounceClickListener(action = ::goBack)
    }

    fun setErrorInterface(errorInterface: ErrorInterface) {
        this.errorInterface = errorInterface
    }

    fun setAction(action: () -> (Unit)) {
        actionFunction = action
    }

    private fun onRetry() {
        errorInterface?.onRetry()
        actionFunction?.invoke()
        dismiss()
    }

    private fun goBack() {
        errorInterface?.onHideError()
        dismiss()
    }
}