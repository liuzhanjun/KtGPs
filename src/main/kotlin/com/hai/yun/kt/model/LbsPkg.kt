package com.hai.yun.kt.model

import org.joda.time.DateTime

data class LbsPkg(
    var time: DateTime? = null,//时间 有些信息包此时间为空
    var lbs_len: Boolean = false,//lbs信息长度是否使用
    var mcc: UShort,//移动用户所属国家代号
    var mnc: UByte,//移动网号码
    var lac: UShort,//位置区码
    var cellId: UInt,//移动基站
    var phone_number: String? = null,//电话号码21位
    var extContent: UByteArray? = null//预留扩展位

)

data class MultipleLbsPkg(
    var time: DateTime,//时间 有些信息包此时间为空
    var mcc: UInt,//移动用户所属国家代号
    var mnc: UByte,//移动网号码
    var lac: UShort,//位置区码
    var mci: UShort,//main移动基站
    var mciss: UByte,//mci信号强度
    var mci_mciss: Array<Mci_Mciss>,//临近小区基站和和小区基站信号强度
    var extContent: UByteArray? = null
)

data class Mci_Mciss(
    var first: UShort,
    var seconde: UByte
)