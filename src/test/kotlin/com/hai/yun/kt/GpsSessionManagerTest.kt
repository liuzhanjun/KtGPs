package com.hai.yun.kt


import com.hai.yun.kt.control.*
import com.hai.yun.kt.model.*
import com.hai.yun.kt.utils.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import org.joda.time.DateTime
import org.junit.Test
import java.io.File
import kotlin.system.measureTimeMillis


class GpsSessionManagerTest {
    val session = GpsSessionManager.session.getInstence()

    //登录包
    @Test
    fun getLoginMsg() {
        val string: String = "123456789012345"
        val loginMsg = session.getLoginMsg(string, 1u).printOxString()
    }

    //解析登录包后台响应
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

        val result = session.analysisMsg(1, bytes2)
        result.getContent().printOxString()
        val toDateTime = result.pContent?.toDateTime()
        println(toDateTime?.toUbyteArray()?.forEach {
            println(it)
        })
        println(toDateTime?.toString("yyyy-MM-dd hh:mm:ss"))
    }

    //GPS信息包
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

    //LBS信息报
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

    //GPS和LBS信息合并包
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

    //终端信息心跳包
    @Test
    fun getHeartBeatPkg() {
        val heartBeatPkg = HeartBeatPkg(
            mTerminalInfo = TerminalInfo(0u, 1u, 1u, 0x001u, 1u, 0u),
            mVoltageGrade = 6u,
            mGSMinfoIntensity = 100u,
            mExt = TerminaExt(0x00u, 0x01u)
        )
        session.getHeartBeatPkg(heartBeatPkg, 1u).printOxString()
    }

    //解析终端信息心跳包
    @Test
    fun analysisHeartBeatPkgMsg() {
        val ubyteArrayOf = ubyteArrayOf(0x78u, 0x78u, 0x05u, 0x13u, 0x00u, 0x11u, 0xF9u, 0x70u, 0x0Du, 0x0Au)
        session.analysisMsg(1, ubyteArrayOf).getContent().printOxString()
    }

    //测试卫星信噪比
    @Test
    fun getSsNoiseRatioPkg() {
        val ssNoiseRatio = SsNoiseRatio(
            5u,
            ubyteArrayOf(0u, 1u, 2u, 3u, 4u, 5u)
        )
        session.getSsNoiseRatioPkg(ssNoiseRatio, 1u).printOxString()
    }

    //测试报警信息
    @Test
    fun getAlarmPkg() {
        val gpsPkg = GpsPkg(
            time = DateTime(2018, 1, 16, 11, 50, 30),
            len = 12u,
            satelliteNumber = 12u,
            point = EarthPoint(Latitude(22, 32.7658), Longitude(22, 32.7658)),
            speed = 255u,
            gpsStateDir = GpsStateDir(332u, 1u, 0u, 1u, 0u)
        )
        val lbsPkg = LbsPkg(
            lbs_len = true,
            mcc = 460u,
            mnc = 0x00u,
            lac = 0xff_feu,
            cellId = 0x00_FF_FE_FFu
        )
        val heartBeatPkg = HeartBeatPkg(
            mTerminalInfo = TerminalInfo(0u, 1u, 1u, 0x001u, 1u, 0u),
            mVoltageGrade = 6u,
            mGSMinfoIntensity = 100u,
            mExt = TerminaExt(0x00u, 0x01u)
        )
        session.getAlarmPkg(gpsPkg, lbsPkg, heartBeatPkg, 1u).printOxString()
    }

    @Test
    fun getMultipleLbsPkg() {
        val multipleLbsPkg = MultipleLbsPkg(
            time = DateTime(2018, 1, 16, 11, 50, 30),
            mcc = 460u,
            mnc = 0x00u,
            lac = 0xff_feu,
            mci = 0xFF_FFu,
            mciss = 0xFFu,
            mci_mciss = arrayOf(
                Mci_Mciss(
                    0xFF_FFu,
                    0xFFu
                ),
                Mci_Mciss(
                    0xFF_FFu,
                    0xFFu
                ),
                Mci_Mciss(
                    0xFF_FFu,
                    0xFFu
                ),
                Mci_Mciss(
                    0xFF_FFu,
                    0xFFu
                ),
                Mci_Mciss(
                    0xFF_FFu,
                    0xFFu
                ),
                Mci_Mciss(
                    0xFF_FFu,
                    0xFFu
                )
            )
        )
        session.getMultipleLbsPkg(multipleLbsPkg, 1u).printOxString()
    }


    @Test
    fun getGPSquery() {
        val data = GpsPkg(
            time = DateTime(2018, 1, 16, 11, 50, 30),
            len = 12u,
            satelliteNumber = 12u,
            point = EarthPoint(Latitude(22, 32.7658), Longitude(22, 32.7658)),
            speed = 255u,
            phone_number = "13800138000",
            gpsStateDir = GpsStateDir(332u, 1u, 0u, 1u, 0u)
        )
        session.getGPSQueryAddressPkg(data, 1u)


        println(ubyteArrayOf(1u, 2u, 3u, 4u).equalsElements(ubyteArrayOf(2u, 1u, 3u, 4u)))
    }


    @Test
    fun analysisGPsQueryAddress() {
        val bytes = ubyteArrayOf(
            0x78u, 0x78u,
            0x00u, 0x9Bu,
            0x97u,
            0x00u, 0x96u,
            0x00u, 0x00u, 0x00u, 0x01u,
            0x41u, 0x44u, 0x44u, 0x52u, 0x45u, 0x53u, 0x53u,
            0x26u, 0x26u,
            0x00u, 0x4cu, 0x00u, 0x6fu, 0x00u, 0x63u, 0x00u, 0x61u, 0x00u, 0x74u, 0x00u,
            0x69u, 0x00u, 0x6fu, 0x00u, 0x6eu, 0x00u, 0x3au, 0x00u, 0x31u, 0x00u, 0x39u,
            0x00u, 0x20u, 0x00u, 0x44u, 0x00u, 0x61u, 0x00u, 0x79u, 0x00u, 0x61u, 0x00u,
            0x6eu, 0x00u, 0x20u, 0x00u, 0x52u, 0x00u, 0x64u, 0x00u, 0x2cu, 0x00u, 0x59u,
            0x00u, 0x75u, 0x00u, 0x6cu, 0x00u, 0x75u, 0x00u, 0x20u, 0x00u, 0x43u, 0x00u,
            0x6fu, 0x00u, 0x6du, 0x00u, 0x6du, 0x00u, 0x75u, 0x00u, 0x6eu, 0x00u, 0x69u,
            0x00u, 0x74u, 0x00u, 0x79u, 0x00u, 0x2cu, 0x00u, 0x53u, 0x00u, 0x68u, 0x00u,
            0x65u, 0x00u, 0x6eu, 0x00u, 0x7au, 0x00u, 0x68u, 0x00u, 0x65u, 0x00u, 0x6eu,
            0x00u, 0x2cu, 0x00u, 0x47u, 0x00u, 0x75u, 0x00u, 0x61u, 0x00u, 0x6eu, 0x00u,
            0x67u, 0x00u, 0x64u, 0x00u, 0x6fu, 0x00u, 0x6eu, 0x00u, 0x67u,
            0x26u, 0x26u,
            0x31u, 0x33u, 0x38u, 0x30u, 0x30u, 0x31u, 0x33u, 0x38u, 0x30u, 0x30u,
            0x30u, 0x20u, 0x20u, 0x20u, 0x20u, 0x20u, 0x20u, 0x20u, 0x20u, 0x20u, 0x20u,
            0x23u, 0x23u,
            0x01u, 0x06u,
            0x50u, 0x11u,
            0x0Du, 0x0Au
        )
        session.analysisGPsQueryAddress(bytes, true) {

            println(it.checked())
            println(it.address_content.unicodeToString())

        }
    }


    @Test
    fun queryAddressLbs() {
        val lbsPkg = LbsPkg(
            lbs_len = true,
            mcc = 460u,
            mnc = 0x00u,
            lac = 0xff_feu,
            phone_number = "18873811258",
            cellId = 0x00_FF_FE_FFu
        )
        session.getLbsQuearyAddressPkg(lbsPkg, 1u).printOxString()
    }

    @Test
    fun getMutipleLbsWifiLoationPkg() {
        val mlwl = MutipleLbsWifiLoation(
            time = DateTime(2018, 1, 16, 11, 50, 30),
            mcc = 460u,
            mnc = 0x00u,
            lac_ci_rssi = arrayOf(Lac_ci_rssi(5u, ubyteArrayOf(4u, 5u, 7u)))
            , timeAdvanced = 0x05u,
            wifiNumber = 0x01u,
            wifiInfo = arrayOf(WifiInfo(ubyteArrayOf(0u, 1u, 2u, 3u, 4u, 5u, 6u), 0x05u))
        )
        session.getMutipleLbsWifiLoationPkg(mlwl, 1u).printOxString()
    }

    @Test
    fun getIMSIPkg() {
        session.getIMSIPkg(ImsiPkg(imsiNumber = "460595485214565")).printOxString()
    }

    @Test
    fun sendICCID() {
        val addIccID = IccIdPkg(0x0Au)
            .addIMEI("0358091088001558")
            .addIMSI("0460041990205313")
            .addIccID("898607b9111730120313")
        session.getICCIdPkg(addIccID, 1u).printOxString()

    }

    //测试录音协议包
    @Test
    fun getRecordPkg() {
        var file = File("src/main/resources/files/lufei.jpg")
        val spliteFile = SpliteRecord(file, 0u)
        spliteFile.spliteFile(3)
        spliteFile.records.forEachIndexed { index, v ->
            println(v)
            val recordPkg = session.getRecordPkg(v, index.toUShort())
            recordPkg.printOxString()
        }
    }

    @Test
    fun testRecordAnaly() {
        val ubyteArrayOf = ubyteArrayOf(0x79u, 0x79u, 0x0u, 0x08u, 0x8du, 0x0Fu,0x0Eu,0x0Fu, 0x00u, 0x01u, 0x0du, 0x0cu, 0x0du, 0x0au)
        val analysisMsg = session.analysisMsg(2, ubyteArrayOf)
        println(analysisMsg)
        println(analysisMsg.getContent().printOxString())
    }
}


