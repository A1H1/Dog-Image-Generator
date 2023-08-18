package com.dogimagegenerator.app.base

import android.util.Log
import com.dogimagegenerator.app.data.error.ErrorData
import com.dogimagegenerator.app.data.error.ErrorResponse
import com.dogimagegenerator.app.data.error.ErrorType
import com.dogimagegenerator.app.network.NoConnectivityException
import com.squareup.moshi.Moshi
import org.koin.java.KoinJavaComponent.getKoin
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

object ErrorHandler {
    fun parseError(throwable: Throwable): ErrorData {
        Log.e("Error", throwable.stackTraceToString())
        return when (throwable) {
            is HttpException -> {
                val response = throwable.response()

                try {
                    val errorResponseAdapter =
                        (getKoin().get() as Moshi).adapter(ErrorResponse::class.java)
                    val errorResponse =
                        errorResponseAdapter.fromJson(response?.errorBody()?.string().orEmpty())
                            ?: return ErrorData()

                    ErrorData(
                        errorType = ErrorType.OTHER,
                        errorResponse = errorResponse
                    )
                } catch (e: Exception) {
                    ErrorData()
                }
            }

            is SocketTimeoutException -> ErrorData(errorType = ErrorType.SERVER_TIMEOUT)
            is IOException -> {
                if (throwable is NoConnectivityException) ErrorData(errorType = ErrorType.NO_INTERNET)
                else ErrorData()
            }

            else -> ErrorData()
        }
    }
}