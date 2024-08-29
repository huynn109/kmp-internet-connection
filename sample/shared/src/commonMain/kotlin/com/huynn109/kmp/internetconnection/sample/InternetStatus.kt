package com.huynn109.kmp.internetconnection.sample

import com.huynn109.kmp.internetconnection.InternetConnection
import kotlinx.coroutines.flow.MutableStateFlow

expect class InternetStatus {
    val current: MutableStateFlow<Boolean>
    fun start()
    fun stop()
}