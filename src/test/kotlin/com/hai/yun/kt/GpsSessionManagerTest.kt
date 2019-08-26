package com.hai.yun.kt


import com.hai.yun.kt.model.*
import com.hai.yun.kt.utils.*
import org.joda.time.DateTime
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.util.*


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
        println(toDateTime?.toUbyteArray()?.forEach {
            println(it)
        })
        println(toDateTime?.toString("yyyy-MM-dd hh:mm:ss"))
    }

    @Test
    fun getGpsInfoPkg() {
        val gpsPkg = GpsPkg(
            time = DateTime(2018, 1, 16, 11, 50, 30),
            len = 12u,
            satelliteNumber = 12u,
            point = EarthPoint(Latitude(22, 32.7658), Longitude(22, 32.7658)),
            speed = 255u,
            gpsStateDir = GpsStateDir(332u, 1u, 0u, 1u, 0u)
        )

        val gpsInfoPkg = session.getGpsInfoPkg(gpsPkg, 1u)
        gpsInfoPkg.printOxString()
    }

    @Test
    fun getLbsInfoPkg() {
        val lbsPkg = LbsPkg(
            time = DateTime(2018, 1, 16, 11, 50, 30),
            mcc = 460u,
            mnc = 0x00u,
            lac = 0xff_feu,
            cellId = 0x00_FF_FE_FFu
        )
        session.getLbsInfoPkg(lbsPkg, 1u).printOxString()
    }

    @Test
    fun getGpsAndLbsPkg() {
        val gpsPkg = GpsPkg(
            time = DateTime(2018, 1, 16, 11, 50, 30),
            len = 12u,
            satelliteNumber = 12u,
            point = EarthPoint(Latitude(22, 32.7658), Longitude(22, 32.7658)),
            speed = 255u,
            gpsStateDir = GpsStateDir(332u, 1u, 0u, 1u, 0u)
        )
        val lbsPkg = LbsPkg(
            mcc = 460u,
            mnc = 0x00u,
            lac = 0xff_feu,
            cellId = 0x00_FF_FE_FFu
        )
        session.getGpsAndLbsPkg(gpsPkg, lbsPkg, 1u).printOxString()
    }

}
