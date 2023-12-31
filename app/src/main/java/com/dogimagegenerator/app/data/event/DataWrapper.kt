package com.dogimagegenerator.app.data.event

import com.dogimagegenerator.app.data.error.ErrorData

data class DataWrapper<T>(
    val data: T? = null,
    val error: ErrorData? = null,
    val isLoading: Boolean = false,
    val type: DataType? = null
)

enum class DataType {
    SUCCESS,
    ERROR
}

data class ResponseWrapper<T>(
    val data: T? = null,
    val error: ErrorData? = null,
    val type: ResponseDataType = ResponseDataType.SUCCESS
)

enum class ResponseDataType {
    SUCCESS,
    ERROR
}