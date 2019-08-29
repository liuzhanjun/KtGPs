package com.hai.yun.kt.control

import com.hai.yun.kt.model.RecordPkg
import com.hai.yun.kt.utils.toUBytes
import com.hai.yun.kt.utils.toUbytes

fun RecordPkg.getContent(): UByteArray {
    val bytes = mutableListOf<UByte>()
    bytes.add(fileType)
    bytes.addAll(fileLen.toUbytes(4))
    bytes.add(filePkgNum)
    bytes.add(fileCurPkgIndex)
    bytes.addAll(curContentLen.toUBytes())
    bytes.addAll(fileDataContent)
    return bytes.toUByteArray()
}