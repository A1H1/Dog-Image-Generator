package com.dogimagegenerator.app.network

import android.content.Context
import com.dogimagegenerator.app.BuildConfig
import com.dogimagegenerator.app.data.api.DogAPIService
import com.dogimagegenerator.app.utils.AppConst
import com.dogimagegenerator.app.utils.AppConst.SERVER_TIMEOUT_CONNECT
import com.dogimagegenerator.app.utils.AppConst.SERVER_TIMEOUT_READ
import com.dogimagegenerator.app.utils.AppConst.SERVER_TIMEOUT_WRITE
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {
    fun provideClient(moshi: MoshiConverterFactory, context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConst.BASE_URL)
            .client(getHttpClient(context))
            .addConverterFactory(moshi)
            .build()
    }

    fun provideMoshiFactory(): MoshiConverterFactory =
        MoshiConverterFactory.create(Moshi.Builder().add(ApplicationJsonAdapterFactory).build())

    fun provideDogAPIService(retrofit: Retrofit): DogAPIService {
        return retrofit.create(DogAPIService::class.java)
    }

    private fun getHttpClient(context: Context): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ConnectivityInterceptor(context))


        return client.connectTimeout(SERVER_TIMEOUT_CONNECT, TimeUnit.SECONDS)
            .writeTimeout(SERVER_TIMEOUT_READ, TimeUnit.SECONDS)
            .readTimeout(SERVER_TIMEOUT_WRITE, TimeUnit.SECONDS)
            .build()
    }
}