package com.hai.yun.kt.model

import org.joda.time.DateTime

data class LbsPkg(
    var time: DateTime? = null,//时间 有些信息包此时间为空
    var lbs_len: Boolean = false,//lbs信息长度是否使用
    var mcc: UInt,//移动用户所属国家代号
    var mnc: UByte,//移动网号码
    var lac: UShort,//位置区码
    var cellId: UInt,//移动基站
    var extContent: UByteArray? = null//预留扩展位

)