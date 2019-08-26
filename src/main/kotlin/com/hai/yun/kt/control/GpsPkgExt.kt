package com.hai.yun.kt.control

import com.hai.yun.kt.model.GpsPkg
import com.hai.yun.kt.utils.toOxArray
import com.hai.yun.kt.utils.toUbyteArray
import com.hai.yun.kt.utils.toUbytes

fun GpsPkg.getGPSInfoContent(): UByteArray {

    val bytes = mutableListOf<UByte>()
    //时间
    bytes.addAll(time.toUbyteArray())
    //GPS信息长度and卫星数
    val gpslenAndNumber = getGpslenAndNumberUbyte()
    bytes.add(gpslenAndNumber)
    //纬度
    //经度
    val pointUbytes = getPointUbytes(4)
    bytes.addAll(pointUbytes)
    //速度
    bytes.add(speed)
    //状态和方向
    val stateAndDire = getStateAndDire()
    bytes.addAll(stateAndDire)
    //扩展位
    if (ext_content != null) {
        bytes.addAll(ext_content!!)
    }
    return bytes.toUByteArray()
}

fun GpsPkg.getGpslenAndNumberUbyte(): UByte {
    return (((len and 0x0Fu) shl 4) or (satelliteNumber and 0x0Fu)).toUByte()
}

fun GpsPkg.getStateAndDire(): UByteArray {
    val (run_dir_c,
        latitude_dir,
        longitude_dir,
        gps_state,
        gps_time) = gpsStateDir
    //运行方向
    val dir_c = run_dir_c and 0x03FFu//取10位
    val la_d = latitude_dir shl 10
    val lo_d = longitude_dir shl 11
    val gs = gps_state shl 12
    val gt = gps_time shl 13
    return (dir_c or la_d or lo_d or gs or gt).toUbytes(2)
}

fun GpsPkg.getPointUbytes(len_bit: Int): UByteArray {
    val (latiude, longitude) = point
    val (la_c, la_m) = latiude
    val (lo_c, lo_m) = longitude
    val latiudeUbytes = ((la_c * 60 + la_m) * 30000).toUInt().toUbytes(len_bit)
    val longitudeUbytes = ((lo_c * 60 + lo_m) * 30000).toUInt().toUbytes(len_bit)
    return mutableListOf<UByte>().apply {
        addAll(latiudeUbytes)
        addAll(longitudeUbytes)
    }.toUByteArray()
}

