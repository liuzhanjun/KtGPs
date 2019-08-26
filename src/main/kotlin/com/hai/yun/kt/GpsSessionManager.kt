package com.hai.yun.kt

import com.hai.yun.bean.AgreeMentNos
import com.hai.yun.kt.model.DataPkg
import com.hai.yun.kt.model.GpsPkg
import com.hai.yun.kt.model.PkgInfo
import com.hai.yun.kt.model.getPkgInfo
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
     *
     * @author liuzhanjun
     * @date 2019/8/24 14:15
     * @param [IMEI, no_] imei    no_序列号
     * @return
     */
    fun getLoginMsg(IMEI: String, no_: UShort): UByteArray {
        return PkgInfo(1).let {
            it.pAgreement = AgreeMentNos.loginNO
            it.pContent = IMEI.oxToUBytes()
            it.pInfoNo = no_
            it.getPkgInfo()
        }
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

    private fun getDataPkg(data: UByteArray, p_len_bit: Int): DataPkg {
        data.let {
            val p_start = ubyteArrayOf(it[0], it[1]).toUShort()
            var p_len: UByteArray? = null
            if (p_len_bit == 1) {
                p_len = ubyteArrayOf(it[2])
            } else if (p_len_bit == 2) {
                p_len = ubyteArrayOf(it[2], it[3])
            }

            var p_content: UByteArray? = null
            //如果长度大于5，说明内容不为空
            val len = p_len?.toUShort()
            if (len != 5.toUShort()) {
                val temp = mutableListOf<UByte>()
                for (i in (5 + p_len_bit)..(len!!.toInt())) {
                    temp.add(it[i])
                }
                p_content = temp.toUByteArray()
            }

            return DataPkg(
                pStartBit = p_start,
                pLength = p_len!!,
                pAgreement = it[2 + p_len_bit],
                pContent = p_content,
                pInfoNo = ubyteArrayOf(it[3 + p_len_bit], it[4 + p_len_bit]).toUShort(),
                pCheckBit = ubyteArrayOf(it[data.size - 4], it[data.size - 3]).toUShort(),
                pStopBit = ubyteArrayOf(it[data.size - 2], it[data.size - 1]).toUShort()
            )
        }
    }


//    fun getGpsPkg(info: GpsPkg): UByteArray {
//
//
//
//    }

}

