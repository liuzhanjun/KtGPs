package com.hai.yun.kt.control

import com.hai.yun.kt.GpsSessionManager
import com.hai.yun.kt.model.DataPkg
import com.hai.yun.kt.model.PkgInfo
import com.hai.yun.kt.model.getPkgInfo
import com.hai.yun.kt.utils.toUShort

internal fun GpsSessionManager.getDataPkg(data: UByteArray, p_len_bit: Int): DataPkg {
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

internal fun GpsSessionManager.getPkgInfo(agreement: UByte, content: UByteArray, no_: UShort?): UByteArray {
    return getPkgInfo(0x78_78u, agreement, content, no_, 1)
}

internal fun GpsSessionManager.getPkgInfo2(agreement: UByte, content: UByteArray, no_: UShort?): UByteArray {
    return getPkgInfo(0x79_79u, agreement, content, no_, 2)
}

internal fun GpsSessionManager.getPkgInfo(
    start: UShort,
    agreement: UByte,
    content: UByteArray,
    no_: UShort?,
    p_len_bit: Int
): UByteArray {
    return PkgInfo(p_len_bit).let {
        it.pStartBit = start
        it.pAgreement = agreement
        it.pContent = content
        it.pInfoNo = no_
        it.getPkgInfo()
    }
}