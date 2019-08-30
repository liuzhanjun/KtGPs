package com.hai.yun.kt.utils

import com.hai.yun.kt.model.DataPkg
import org.joda.time.DateTime
import java.time.Year
import java.util.*


fun UByteArray.toDateTime(): DateTime {
//    val bigYear =year1-(year1 %100)
    val currentYear = DateTime(Date()).year().get()
    val year = (currentYear - (currentYear % 100)) + this[0].toInt()
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

//年份只取最后两位
//2016 取16   2155 取55
// 把时间转成无符号字节数组
fun DateTime.toUbyteArray(): UByteArray {
    val currentYear = DateTime(Date()).year().get()
    val year = this.year().get() - (currentYear - (currentYear % 100))
    val monthOfYear = this.monthOfYear().get()
    val dayOfMonth = this.dayOfMonth().get()
    val hourOfDay = this.hourOfDay().get()
    val minuteOfHour = this.minuteOfHour().get()
    val secondOfMinute = this.secondOfMinute().get()
    return ubyteArrayOf(
        year.toUByte(),
        monthOfYear.toUByte(),
        dayOfMonth.toUByte(),
        hourOfDay.toUByte(),
        minuteOfHour.toUByte(),
        secondOfMinute.toUByte()
    )
}