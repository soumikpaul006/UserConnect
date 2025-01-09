package com.codegalaxy.mock8janss

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast

class ConnectivityReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
       val connectivityManager=
           context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network= connectivityManager.activeNetwork

        val capabilities= connectivityManager.getNetworkCapabilities(network)

        val isConnected=capabilities!=null &&(
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                )


        if(isConnected)
        {
            Toast.makeText(context,"Internet is enable",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Internet is disable",Toast.LENGTH_SHORT).show()

        }


    }
}