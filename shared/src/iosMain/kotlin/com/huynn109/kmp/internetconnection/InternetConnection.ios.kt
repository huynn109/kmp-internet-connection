package com.huynn109.kmp.internetconnection

import kotlinx.coroutines.flow.MutableStateFlow

actual class InternetConnection {
    actual val isNetworkConnected: MutableStateFlow<Boolean>
        get() = TODO("Not yet implemented")

    actual fun start() {
    }

    actual fun stop() {
    }

    actual fun getStatus(success: (Boolean) -> Unit) {
    }
}