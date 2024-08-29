package com.huynn109.kmp.internetconnection.sample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform