package com.hai.yun.kt.control

import com.hai.yun.kt.model.CommandPkg
import com.hai.yun.kt.utils.oxToUBytes
import com.hai.yun.kt.utils.stringToAscii_ox
import com.hai.yun.kt.utils.toUShorts
import com.hai.yun.kt.utils.toUbytes

fun CommandPkg.getContent(): UByteArray {
    val bytes = mutableListOf<UByte>()
    bytes.add(cmLen)
    bytes.addAll(serviceFlag.toUbytes(4))
//    bytes.addAll(cmContent.stringToAscii_ox().oxToUBytes())
    bytes.addAll(cmContent)
    if (extContent != null) {
        bytes.addAll(extContent!!)
    }
    return bytes.toUByteArray()

}