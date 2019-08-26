package com.hai.yun.kt.model

import org.joda.time.DateTime

data class LbsPkg(
    var time: DateTime? =null,//时间
    var mcc: UInt,//移动用户所属国家代号
    var mnc: UByte,//移动网号码
    var lac: UShort,//位置区码
    var cellId: UInt,//移动基站
    var extContent: UByteArray? = null//预留扩展位

)