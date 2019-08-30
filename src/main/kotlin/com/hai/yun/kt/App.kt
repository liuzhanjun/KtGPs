package com.hai.yun.kt

import com.hai.yun.kt.net.MinaTcpClient
import com.hai.yun.kt.utils.*
import kotlin.math.log

fun main() {
    val session = GpsSessionManager.session.getInstence()
    MinaTcpClient.client.init()

    MinaTcpClient.client.sendMessage(GpsSessionManager.session.getLoginMsg("123456789012345",1u))

}