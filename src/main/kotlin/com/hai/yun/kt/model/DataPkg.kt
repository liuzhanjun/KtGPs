package com.hai.yun.kt.model

import com.hai.yun.kt.utils.toOx
import com.hai.yun.kt.utils.toOxArray

/**
@author liuzhanjun
 **/
data class DataPkg(
    var pStartBit: UShort,//起始位
    var pLength: UByteArray,//包长度
    var pAgreement: UByte,//协议号
    var pContent: UByteArray?,//信息内容
    var pInfoNo: UShort,//信息序列号
    var pCheckBit: UShort,//校验位
    var pStopBit: UShort = 0x0D_0Au//停止位
) {
    override fun toString(): String {
        return "DataPkg(" +
                "\n pStartBit=${pStartBit.toOxArray()}," +
                "\n pLength=${pLength.toOxArray()}," +
                "\n pAgreement=${pAgreement.toOx()}," +
                "\n pContent=${pContent?.toOxArray()}, " +
                "\n pInfoNo=${pInfoNo.toOxArray()}," +
                "\n pCheckBit=${pCheckBit.toOxArray()}," +
                "\n pStopBit=${pStopBit.toOxArray()})"
    }
}