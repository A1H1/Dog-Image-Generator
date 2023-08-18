package com.dogimagegenerator.app.network

import android.content.Context
import com.dogimagegenerator.app.utils.AppUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor(private val context: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (AppUtils.isNetworkAvailable(context).not()) {
            throw NoConnectivityException()
        }
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}