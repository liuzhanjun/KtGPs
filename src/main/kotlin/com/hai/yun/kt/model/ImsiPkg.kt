package com.hai.yun.kt.model

/**
@author liuzhanjun
 **/
data class ImsiPkg(
    val imsiHead: UByteArray = ubyteArrayOf(0x49u, 0x4Du, 0x53u, 0x49u, 0x3Au),
    var imsiNumber: String,
    var extContent: UByteArray? = ubyteArrayOf(0x01u, 0x01u),//预留扩展位
    val endString: UByte = 0x9fu
)