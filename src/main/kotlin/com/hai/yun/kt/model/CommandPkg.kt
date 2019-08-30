package com.hai.yun.kt.model

/**
@author liuzhanjun
 **/
data class CommandPkg(
    var cmLen: UByte,//指令长度
    var serviceFlag: UInt,//服务器标志位
    var cmContent: UByteArray,//指令内容
    var extContent: UByteArray? = null//扩展内容
)