package com.huynn109.kmp.internetconnection.sample

import com.huynn109.kmp.internetconnection.InternetConnection

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}