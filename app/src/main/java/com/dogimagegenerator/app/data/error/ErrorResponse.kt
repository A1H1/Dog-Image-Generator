package com.dogimagegenerator.app.data.error

data class ErrorResponse(
    val status: String? = null,
    val message: String? = null
)

enum class ErrorType {
    OTHER,
    UNAUTHORIZED_ACCESS,
    VALIDATION_ERROR,
    FORBIDDEN_ERROR,
    NOT_FOUND,
    SERVER_TIMEOUT,
    NO_INTERNET
}

data class ErrorData(
    val errorResponse: ErrorResponse = ErrorResponse(),
    val errorType: ErrorType = ErrorType.OTHER
)