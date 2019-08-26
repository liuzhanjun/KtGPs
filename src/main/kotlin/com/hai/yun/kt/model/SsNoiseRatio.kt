package com.hai.yun.kt.model

/**
 * 卫星信噪比
@author liuzhanjun
 **/
data class SsNoiseRatio(
    var mNumber:UByte,//卫星数，最多15个
    var noiseRatioList: UByteArray,//卫星信噪比列表
    var extContent: UByteArray//预留扩展位
)