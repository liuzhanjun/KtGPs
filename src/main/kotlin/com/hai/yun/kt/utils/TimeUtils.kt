package com.hai.yun.kt.utils

import com.hai.yun.kt.model.DataPkg
import org.joda.time.DateTime


fun UByteArray.toDateTime(): DateTime {
    val year = 2000 + this[0].toInt()
    val month = this[1].toInt()
    val day = this[2].toInt()
    val hours = this[3].toInt()
    val minute = this[4].toInt()
    val seconde = this[5].toInt()
    return DateTime(year, month, day, hours, minute, seconde)
}

/**
 * 返回dataPkg日期
 */
fun DataPkg.toDateTime(): DateTime {
    return this.pContent!!.toDateTime()
}