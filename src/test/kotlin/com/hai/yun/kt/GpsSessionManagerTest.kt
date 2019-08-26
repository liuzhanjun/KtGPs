package com.hai.yun.kt


import com.hai.yun.kt.utils.toDateTime
import com.hai.yun.kt.utils.toOxArray
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*


class GpsSessionManagerTest {
    val session = GpsSessionManager.session.getInstence()
    @Test
    fun getLoginMsg() {
        val string: String = "123456789012345"
        val loginMsg = session.getLoginMsg(string, 65535u)
        println("array" + loginMsg.toOxArray())
    }

    @Test
    fun getResponseMsg() {
        val bytes: UByteArray = ubyteArrayOf(
            0x78u, 0x78u, 0x05u, 0x01u, 0x00u, 0x01u,
            0xd9u, 0xdcu, 0x0du, 0x0au
        )
        val bytes2 = ubyteArrayOf(
            0x78u, 0x78u, 0x0Bu, 0x01u, 0x00u, 0x01u, 0x12u, 0x01u, 0x08u, 0x09u,
            0x1eu, 0x0au, 0xd9u, 0xdcu, 0x0du, 0x0au
        )
//    val (pStartBit,
//        pLength,
//        pAgreement,
//        pContent,
//        pInfoNo,
//        pCheckBit,
//        pStopBit)

        val result = session.getResponseMsg(1, bytes2)
        val toDateTime = result.pContent?.toDateTime()
        println(toDateTime?.toString("yyyy-MM-dd hh:mm:ss"))
        println(bytes2.toOxArray())
        println(result.toString())

        println(result.pContent?.toOxArray())
        println(result.pStopBit.toOxArray())
    }
}
