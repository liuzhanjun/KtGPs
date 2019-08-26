package com.hai.yun.kt.model

import org.joda.time.DateTime

/**
@author liuzhanjun
 gps信息包
 **/
data class GpsPkg(
    var time: DateTime,//时间日期
    var len: Int,//信息长度
    var satelliteNumber: Int,//卫星数
    var point: EarthPoint,//经纬度
    var speed: Int = 0,//速度
    var gpsStateDir: GpsStateDir,//gps状态信息
    var ext_content: UByteArray,//预留扩展位
    var phone_number: String//电话号码

)

class GpsStateDir(
    var run_dir_c: Int,// 运行方向度数 0-360°
    var latitude_dir: Int,// 纬度方向 0：南纬 1：北纬
    var longitude_dir: Int,// 经度方向 0：东经 1：西经
    var gps_state: Int,// GPS定位状态  0：GPS不定位 1：GPS已定位
    var gps_time: Int// GPS类型  0：实时GPS  1差分GPS
)

class Latitude(
    var latitude_c: Int = 0,//度
    var latitude_m: Double = 0.toDouble()//分
)

class Longitude(
    var longitude_c: Int = 0,//度
    var longitude_m: Double = 0.toDouble()//分
)


class EarthPoint(
    val latitude: Latitude,//纬度
    val longitude: Longitude//经度
)