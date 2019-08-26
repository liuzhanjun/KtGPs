package com.hai.yun.kt.control

import com.hai.yun.kt.model.DataPkg
import com.hai.yun.kt.utils.toUBytes

fun DataPkg.getContent(): UByteArray {
    val bytes = mutableListOf<UByte>()
    bytes.addAll(pStartBit.toUBytes())
    bytes.addAll(pLength)
    bytes.add(pAgreement)
    if (pContent != null) {
        bytes.addAll(pContent!!)
    }
    bytes.addAll(pInfoNo.toUBytes())
    bytes.addAll(pCheckBit.toUBytes())
    bytes.addAll(pStopBit.toUBytes())
    return bytes.toUByteArray()
}