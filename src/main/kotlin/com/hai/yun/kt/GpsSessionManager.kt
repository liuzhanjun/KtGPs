package com.hai.yun.kt

import com.hai.yun.bean.AgreeMentNos
import com.hai.yun.kt.control.*
import com.hai.yun.kt.control.getContent
import com.hai.yun.kt.control.getDataPkg
import com.hai.yun.kt.control.getGPSInfoContent
import com.hai.yun.kt.control.getPkgInfo
import com.hai.yun.kt.model.*
import com.hai.yun.kt.utils.*
import java.lang.IndexOutOfBoundsException

enum class GpsSessionManager {
    session {
        override fun getInstence(): GpsSessionManager {
            return this;
        }
    };

    abstract fun getInstence(): GpsSessionManager


    /**
     *
     *获得登录包信息0x01u
     * @author liuzhanjun
     * @date 2019/8/24 14:15
     * @param [IMEI, no_] imei    no_序列号
     * @return
     */
    fun getLoginMsg(IMEI: String, no_: UShort): UByteArray {
        val content = IMEI.oxToUBytes()
        return getPkgInfo(AgreeMentNos.loginNO, content, no_)
    }


    /**
     *
     * 解析后台响应
     * @author liuzhanjun
     * @date 2019/8/24 17:13
     * @param [p_len_bit, data] 长度位宽，接收到的数据
     * @return
     */
    fun analysisMsg(p_len_bit: Int, data: UByteArray): DataPkg {
        if (p_len_bit > 2 || p_len_bit < 1) {
            throw IndexOutOfBoundsException()
        }
        return getDataPkg(data, p_len_bit)

    }


    /**
     *
     * gps信息包0x10u
     * @author liuzhanjun
     * @date 2019/8/26 9:51
     * @param [info, no_]
     * @return
     */
    fun getGpsInfoPkg(info: GpsPkg, no_: UShort): UByteArray {
        return getPkgInfo(AgreeMentNos.gpsInfo, info.getGPSInfoContent(), no_)
    }

    /**
     *
     * 获得lbs信息包
     * @author liuzhanjun
     * @date 2019/8/26 11:25
     * @param [info, no_]
     * @return
     */
    fun getLbsInfoPkg(info: LbsPkg, no_: UShort): UByteArray {
        return getPkgInfo(AgreeMentNos.LBSInfo, info.getContent(), no_)
    }


    /**
     *  GPS.lbs合并包
     * 0x12u
     * @author liuzhanjun
     * @date 2019/8/26 14:03
     * @param [gps, lbs, no_]
     * @return
     */
    fun getGpsAndLbsPkg(gps: GpsPkg, lbs: LbsPkg, no_: UShort): UByteArray {
        return getPkgInfo(AgreeMentNos.GPSAndLBSInfo, gps.getGpsAndLbsContent(lbs), no_)
    }

    /**
     *
     * 心跳包
     * @author liuzhanjun
     * @date 2019/8/26 14:48
     * @param [beat, no_]
     * @return
     */
    fun getHeartBeatPkg(beat: HeartBeatPkg, no_: UShort): UByteArray {
        return getPkgInfo(AgreeMentNos.heartbeat, beat.getContent(), no_)
    }


    /**
     * 卫星信噪比
     */
    fun getSsNoiseRatioPkg(ssnrp: SsNoiseRatio, no_: UShort): UByteArray {
        return getPkgInfo(AgreeMentNos.SSNIRInfo, ssnrp.getContent(), no_)
    }

    /**
     *
     * 获得gps和lbs信息包和报警信息合并
     * @author liuzhanjun
     * @date 2019/8/26 16:18
     * @param [gps, lbs, beart, no_]
     * @return
     */
    fun getAlarmPkg(gps: GpsPkg, lbs: LbsPkg, beart: HeartBeatPkg, no_: UShort): UByteArray {
        return getPkgInfo(AgreeMentNos.WarningInfo, gps.getGPSAndLbsAndAlarm(lbs, beart), no_)
    }


    /**
     * lbs多基站定位信息包
     */
    fun getMultipleLbsPkg(data: MultipleLbsPkg, no_: UShort): UByteArray {
        return getPkgInfo(AgreeMentNos.LBSMultipleBaseStations, data.getContent(), no_)
    }

    /**
     *
     * gps地址查询信息包
     * @author liuzhanjun
     * @date 2019/8/26 17:29
     * @param [data, no_]
     * @return
     */
    fun getGPSQueryAddressPkg(data: GpsPkg, no_: UShort): UByteArray {
        return getPkgInfo(AgreeMentNos.queryAddressGPS, data.getGPSInfoContent(), no_)
    }

    /**
     *
     *
     * @author liuzhanjun
     * @date 2019/8/27 9:15
     * @param [data]
     * @return
     */
    fun analysisGPsQueryAddress(data: UByteArray, isEnglish: Boolean, callback: (AnalysisAddressInfo) -> Unit) {
        callback(AnalysisAddressInfo().analysis(data, isEnglish))
    }


    /**0x17u
     * 查询lbs地址
     */
    fun getLbsQuearyAddressPkg(data: LbsPkg, no_: UShort): UByteArray {
        return getPkgInfo(AgreeMentNos.queryAddressLBS, data.getContent(), no_)
    }

    /**
     * 0x2Cu
     * lbs多基站wifi定位信息
     */
    fun getMutipleLbsWifiLoationPkg(data: MutipleLbsWifiLoation, no_: UShort): UByteArray {
        return getPkgInfo(AgreeMentNos.LBSWIFIMultipBaseStations, data.getContent(), no_)
    }

    /**
     * IMSI
     *0x90u
     */
    fun getIMSIPkg(data: ImsiPkg): UByteArray {
        return getPkgInfo(AgreeMentNos.IMSISendInfo, data.getContent(), null)
    }


    /**
     * 发送ICCID
     * 0x94u
     */
    fun getICCIdPkg(data: IccIdPkg, no_: UShort): UByteArray {
        return getPkgInfo2(AgreeMentNos.ICCIDSendInfo, data.getContent(), no_)
    }

}



