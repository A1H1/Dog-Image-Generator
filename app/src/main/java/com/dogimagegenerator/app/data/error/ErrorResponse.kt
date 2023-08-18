package com.dogimagegenerator.app.data.error

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class ErrorResponse(
    @field:Json(name = "status")
    val status: String? = null,
    @field:Json(name = "message")
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