package com.hai.yun.kt.control

import com.hai.yun.kt.model.ImsiPkg
import com.hai.yun.kt.utils.oxToUBytes
import com.hai.yun.kt.utils.string2Unicode

fun ImsiPkg.getContent(): UByteArray {
    return mutableListOf<UByte>().let {
        it.addAll(imsiHead)
        it.addAll(imsiNumber.string2Unicode().oxToUBytes())
        if (extContent != null) {
            it.addAll(extContent!!)
        }
        it.add(endString)
        it.toUByteArray()
    }
}