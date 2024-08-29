package com.huynn109.kmp.internetconnection

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.util.Log
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Time

actual class InternetConnection(
    private val context: Context,
) : ConnectivityManager.NetworkCallback() {
    private var connectivityManager: ConnectivityManager? = null
    actual val isNetworkConnected: MutableStateFlow<Boolean> = MutableStateFlow(false)

    actual fun start() {
        try {
            if (connectivityManager == null) {
                connectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            }

            connectivityManager?.registerDefaultNetworkCallback(this)

            val currentNetwork = connectivityManager?.activeNetwork

            if (currentNetwork == null) {
                isNetworkConnected.value = false

                Napier.d("No network available")
            }
            Napier.d("InternetConnection started")
        } catch (e: Exception) {
            Napier.e("Error starting InternetConnection ", e)
        }
    }

    actual fun stop() {
        try {
            connectivityManager?.unregisterNetworkCallback(this)
            Napier.d("InternetConnection stopped")
        } catch (e: Exception) {
            Napier.e("Error stopping InternetConnection ", e)
        }
    }

    actual fun getStatus(success: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.Default).launch {
            isNetworkConnected.collect {
                withContext(Dispatchers.Main) {
                    success(it)
                }
            }
        }
    }

    override fun onAvailable(network: Network) {
        Napier.d("Network available")
    }

    override fun onLost(network: Network) {
        Napier.d("Network lost")
    }

    override fun onCapabilitiesChanged(
        network: Network,
        networkCapabilities: NetworkCapabilities
    ) {
        super.onCapabilitiesChanged(network, networkCapabilities)
        val isConnected =
            networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_RESTRICTED) &&
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)

        Napier.d("Internet status: ${if (isConnected) "Connected" else "Disconnected"}")

        isNetworkConnected.value = isConnected
    }
}