package com.codegalaxy.mock8janss.view

import UserScreen
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.codegalaxy.mock8janss.ConnectivityReceiver
import com.codegalaxy.mock8janss.ui.theme.Mock8janSSTheme
import com.codegalaxy.mock8janss.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var connectivityReceiver: ConnectivityReceiver
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mock8janSSTheme {
                UserScreen(viewModel)
            }
        }

        connectivityReceiver=ConnectivityReceiver()
        val intentFilter=IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(connectivityReceiver,intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()

        unregisterReceiver(connectivityReceiver)
    }
}
