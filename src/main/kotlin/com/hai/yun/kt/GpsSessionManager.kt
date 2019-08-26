package com.hai.yun.kt

import com.hai.yun.bean.AgreeMentNos
import com.hai.yun.kt.control.getContent
import com.hai.yun.kt.control.getDataPkg
import com.hai.yun.kt.control.getGPSInfoContent
import com.hai.yun.kt.control.getPkgInfo
import com.hai.yun.kt.model.DataPkg
import com.hai.yun.kt.model.GpsPkg
import com.hai.yun.kt.model.LbsPkg
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
     * 解析接收的服务器响应
     * @author liuzhanjun
     * @date 2019/8/24 17:13
     * @param [p_len_bit, data] 长度位宽，接收到的数据
     * @return
     */
    fun getResponseMsg(p_len_bit: Int, data: UByteArray): DataPkg {
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


}

