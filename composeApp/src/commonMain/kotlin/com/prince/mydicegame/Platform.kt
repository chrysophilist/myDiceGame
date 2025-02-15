package com.prince.mydicegame

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform