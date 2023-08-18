package com.dogimagegenerator.app.base

import com.dogimagegenerator.app.data.error.ErrorData
import com.dogimagegenerator.app.data.error.ErrorResponse
import com.dogimagegenerator.app.data.error.ErrorType

interface BaseErrorInterface {
    fun showError(errorData: ErrorData) {
        when (errorData.errorType) {
            ErrorType.OTHER -> showOtherError(errorData.errorResponse)
            ErrorType.UNAUTHORIZED_ACCESS -> onUnauthorizedAccess(errorData.errorResponse)
            ErrorType.VALIDATION_ERROR -> showValidationError(errorData.errorResponse)
            ErrorType.FORBIDDEN_ERROR -> onForbiddenError(errorData.errorResponse)
            ErrorType.NOT_FOUND -> onNotFound(errorData.errorResponse)
            ErrorType.SERVER_TIMEOUT -> showSocketTimeoutError()
            ErrorType.NO_INTERNET -> showInternetError()
        }
    }

    fun onUnauthorizedAccess(errorResponse: ErrorResponse) {
        showOtherError(errorResponse)
    }

    fun showInternetError() {
        showOtherError(ErrorResponse())
    }

    fun showSocketTimeoutError() {
        showOtherError(ErrorResponse())
    }

    fun onForbiddenError(errorResponse: ErrorResponse) {
        showOtherError(errorResponse)
    }

    fun onNotFound(errorResponse: ErrorResponse) {
        showOtherError(errorResponse)
    }

    fun showValidationError(errorResponse: ErrorResponse) {
        showOtherError(errorResponse)
    }

    fun showOtherError(errorResponse: ErrorResponse)
}