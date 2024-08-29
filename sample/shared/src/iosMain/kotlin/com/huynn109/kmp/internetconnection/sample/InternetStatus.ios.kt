package com.huynn109.kmp.internetconnection.sample

import kotlinx.coroutines.flow.MutableStateFlow

actual class InternetStatus {
    actual val current: MutableStateFlow<Boolean>
        get() = TODO("Not yet implemented")

    actual fun start() {
    }

    actual fun stop() {
    }
}