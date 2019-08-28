package com.hai.yun.kt.control

import com.hai.yun.kt.model.IccIdPkg
import com.hai.yun.kt.utils.oxToUBytes

fun IccIdPkg.addIMEI(imei: String): IccIdPkg {
    dataContent!!.addAll(imei.oxToUBytes())
    return this
}

fun IccIdPkg.addIMSI(imsi: String): IccIdPkg {
    dataContent!!.addAll(imsi.oxToUBytes())
    return this
}

fun IccIdPkg.addIccID(iccid: String): IccIdPkg {
    dataContent!!.addAll(iccid.oxToUBytes())
    return this
}

fun IccIdPkg.addUbytes(data: UByteArray): IccIdPkg {
    dataContent!!.addAll(data)
    return this
}

fun IccIdPkg.getContent(): UByteArray {
    dataContent!!.addFirst(msgType)
    return dataContent!!.toUByteArray()
}
