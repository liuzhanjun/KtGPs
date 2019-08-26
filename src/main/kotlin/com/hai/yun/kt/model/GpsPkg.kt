package com.hai.yun.kt.model

import com.hai.yun.kt.utils.oxToUBytes
import org.joda.time.DateTime

/**
@author liuzhanjun
gps信息包
 **/
data class GpsPkg(
    var time: DateTime,//时间日期
    var len: UInt,//信息长度
    var satelliteNumber: UInt,//卫星数
    var point: EarthPoint,//经纬度
    var speed: UByte,//速度
    var gpsStateDir: GpsStateDir,//gps状态信息
    var ext_content: UByteArray? = null,//预留扩展位
    var phone_number: String? = null//电话号码

)

data class GpsStateDir(
    var run_dir_c: UInt,// 运行方向度数 0-360°
    var latitude_dir: UInt,// 纬度方向 0：南纬 1：北纬
    var longitude_dir: UInt,// 经度方向 0：东经 1：西经
    var gps_state: UInt,// GPS定位状态  0：GPS不定位 1：GPS已定位
    var gps_time: UInt// GPS类型  0：实时GPS  1差分GPS
)

data class Latitude(
    var latitude_c: Int = 0,//度
    var latitude_m: Double = 0.toDouble()//分
)

data class Longitude(
    var longitude_c: Int = 0,//度
    var longitude_m: Double = 0.toDouble()//分
)


data class EarthPoint(
    val latitude: Latitude,//纬度
    val longitude: Longitude//经度
)


