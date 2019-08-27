package com.hai.yun.kt.model

import sun.security.util.Length

/**
@author liuzhanjun
 **/
data class AnalysisAddressInfo(
    var mPstartBit: UShort = 0x78_78u,//起始位
    var mLength: UByteArray = ubyteArrayOf(),//长度
    var mAgreement: UByte = 0u,//协议号
    var mCommandLen: UByteArray = ubyteArrayOf(),//指令长度
    var mServiceFlag: UInt = 0u,//服务器标志
    var ADDRESS: UByteArray = ubyteArrayOf(0x41u, 0x44u, 0x44u, 0x52u, 0x45u, 0x53u, 0x53u),
    var separator: UShort = 0x26_26u,//分隔符
    var address_content: UByteArray = ubyteArrayOf(),//地址
    var phone_number: UByteArray = ubyteArrayOf(),//手机号码
    var endFlag: UShort = 0x23_23u,//结束符
    var list_no: UShort = 0u,//序列号
    var check_bit: UShort = 0u,//校验位
    var stop_bit: UShort = 0u//停止位
)