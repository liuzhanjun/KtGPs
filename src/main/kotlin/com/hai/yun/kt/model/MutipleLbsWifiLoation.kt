package com.hai.yun.kt.model

import org.joda.time.DateTime

/**
 * lbs多基站wifi定位信息
@author liuzhanjun
 **/
data class MutipleLbsWifiLoation(
    var time: DateTime? = null,//时间 有些信息包此时间为空
    var mcc: UShort,//移动用户所属国家代号
    var mnc: UByte,//移动网号码
    var lac_ci_rssi: Array<Lac_ci_rssi>,//7个 不足补0x00
    var timeAdvanced: UByte,//时间提前量
    var wifiNumber: UByte,//wifi数量最多八个
    var wifiInfo: Array<WifiInfo> //最多八个
)

data class Lac_ci_rssi(
    var lac: UShort = 0x00_00u,//位置区码
    var ci: UByteArray = ubyteArrayOf(0x00u, 0x00u, 0x00u),//3位长度移动基站
    var rssi: UByte = 0x00u//小区信号强度
)

data class WifiInfo(
    var wifiMac: UByteArray,//wifimac地址
    var wifiStrong: UByte//wifi强度
)