package com.dogimagegenerator.app.utils

import com.dogimagegenerator.app.BuildConfig

object AppConst {
    const val PREF_FILE = BuildConfig.APPLICATION_ID
    const val PREF_IMAGES = "pref_images"
    const val BASE_URL = "https://dog.ceo/api/"

    const val SERVER_TIMEOUT_CONNECT = 18L
    const val SERVER_TIMEOUT_READ = 60L
    const val SERVER_TIMEOUT_WRITE = 60L
}