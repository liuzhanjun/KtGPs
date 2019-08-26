package com.hai.yun.kt.control

import com.hai.yun.kt.model.HeartBeatPkg

fun HeartBeatPkg.getContent(): UByteArray {
    val bytes = mutableListOf<UByte>()
    addList(bytes)
    return bytes.toUByteArray()
}

internal fun HeartBeatPkg.addList(bytes: MutableList<UByte>) {
    val (mDefense,
        mAcc,
        mAnElectric,
        mWarring,
        mGpsPostion,
        mOilElectricity) = mTerminalInfo
    //终端信息
    bytes.add((mDefense or (mAcc shl 1) or (mAnElectric shl 2) or (mWarring shl 3) or (mGpsPostion shl 6) or (mOilElectricity shl 7)).toUByte())
    //电压等级
    bytes.add(mVoltageGrade)
    //GSM信号轻度
    bytes.add(mGSMinfoIntensity)
    //语言工作模式
    val (first, seconde) = mExt
    bytes.add(first)
    bytes.add(seconde)
}