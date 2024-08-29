package com.huynn109.kmp.internetconnection.sample

import android.content.Context
import com.huynn109.kmp.internetconnection.InternetConnection
import kotlinx.coroutines.flow.MutableStateFlow

actual class InternetStatus(
    context: Context
) {
    val internetConnection = InternetConnection(context)
    actual val current: MutableStateFlow<Boolean> = internetConnection.isNetworkConnected

    actual fun start() {
        internetConnection.start()
    }

    actual fun stop() {
        internetConnection.stop()
    }
}

