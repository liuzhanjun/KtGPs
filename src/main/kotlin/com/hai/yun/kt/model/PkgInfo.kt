package com.hai.yun.kt.model

import com.hai.yun.kt.utils.CRC16
import com.hai.yun.kt.utils.toOxArray
import com.hai.yun.kt.utils.toUBytes
import com.hai.yun.kt.utils.toUbytes

/**
 *
 *
 * @author liuzhanjun
 * @date 2019/8/24 10:26
 * @param p_len_bit 包长度位宽
 * @return
 */
class PkgInfo(val p_len_bit: Int) {
    var pStartBit: UShort = 0x78_78u//起始位
    var pLength: UByteArray = ubyteArrayOf()//包长度
    var pAgreement: UByte = 0x00u//协议号
    var pContent: UByteArray = ubyteArrayOf()//信息内容
    var pInfoNo: UShort = 0x00u//信息序列号
    var pCheckBit: UShort = 0x00u//校验位
    val pStopBit: UShort = 0x0D_0Au //停止位
}

/*
 *  
 * 
 * @author liuzhanjun
 * @date 2019/8/24 10:17
 * @param []
 * @return 
 */
fun PkgInfo.getPkgInfo(): UByteArray {

    //计算包长度字段 协议号长度到校验位长度
    pLength = (5 + pContent.size).toUInt().toUbytes(p_len_bit)
    //计算校验位
    measureCheckBit()
    return mutableListOf<UByte>().let {
        it.addAll(pStartBit.toUBytes())
        it.addAll(pLength)
        it.add(pAgreement)
        it.addAll(pContent)
        it.addAll(pInfoNo.toUBytes())
        it.addAll(pCheckBit.toUBytes())
        it.addAll(pStopBit.toUBytes())
        it.toUByteArray()
    }


}


private fun PkgInfo.measureCheckBit() {
    val checkBit = mutableListOf<UByte>().let {
        it.addAll(pLength)
        it.add(pAgreement)
        it.addAll(pContent)
        it.addAll(pInfoNo.toUBytes())
        CRC16.getCRC16(it.toUByteArray())
    }
    this.pCheckBit = checkBit.toUShort()
}