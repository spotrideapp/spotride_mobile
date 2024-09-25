package com.spotride.mobile

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform