package com.huynn109.kmp.internetconnection

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform