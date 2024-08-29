package com.huynn109.kmp.internetconnection

import kotlinx.coroutines.flow.MutableStateFlow

expect class InternetConnection {
    val isNetworkConnected: MutableStateFlow<Boolean>
    fun start()
    fun stop()
    fun getStatus(success: (Boolean) -> Unit)
}