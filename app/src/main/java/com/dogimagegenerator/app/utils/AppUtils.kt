package com.dogimagegenerator.app.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object AppUtils {
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nw = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_LOWPAN) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI_AWARE) -> true
            else -> false
        }
    }
}