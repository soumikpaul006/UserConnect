package com.codegalaxy.mock8janss.model.networkconnectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import javax.inject.Inject

class NetworkConnectivity @Inject constructor(
    private val context: Context
) {

    private val connectivityManager=
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun isNetworkAvailable():Boolean{
        val network = connectivityManager.activeNetwork
        val cpapablities=connectivityManager.getNetworkCapabilities(network)

        return cpapablities!=null && (
                cpapablities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)||
                        cpapablities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)

                )
    }
}