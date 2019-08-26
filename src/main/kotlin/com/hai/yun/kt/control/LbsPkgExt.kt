package com.hai.yun.kt.control

import com.hai.yun.kt.model.GpsPkg
import com.hai.yun.kt.model.LbsPkg
import com.hai.yun.kt.utils.toUBytes
import com.hai.yun.kt.utils.toUbyteArray
import com.hai.yun.kt.utils.toUbytes

internal fun LbsPkg.getContent(): UByteArray {
    val bytes = mutableListOf<UByte>()
    addLbsList(bytes)
    return bytes.toUByteArray()
}

internal fun LbsPkg.addLbsList(bytes: MutableList<UByte>) {
    //时间
    if (time != null) {
        bytes.addAll(time!!.toUbyteArray())
    }
    //mcc
    bytes.addAll(mcc.toUbytes(2))
    //mnc
    bytes.add(mnc)
    //lac
    bytes.addAll(lac.toUBytes())
    //cellId
    bytes.addAll(cellId.toUbytes(3))
    //ext
    if (extContent != null) {
        bytes.addAll(extContent!!)
    }
}
