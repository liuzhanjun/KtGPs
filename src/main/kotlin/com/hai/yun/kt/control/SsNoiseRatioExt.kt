package com.hai.yun.kt.control

import com.hai.yun.kt.model.SsNoiseRatio

fun SsNoiseRatio.getContent():UByteArray {
    val bytes = mutableListOf<UByte>()
    bytes.add((mNumber and 0x0fu).toUByte())
    noiseRatioList.forEach {
        bytes.add(it)
    }
    if (extContent != null) {
        bytes.addAll(extContent!!)
    }
    return bytes.toUByteArray()

}