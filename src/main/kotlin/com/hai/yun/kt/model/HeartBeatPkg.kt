package com.hai.yun.kt.model

/**
@author liuzhanjun
 **/
data class HeartBeatPkg(
    var mTerminalInfo: TerminalInfo? = null,//终端信息
    //电压等级 取值0-6  0低电关机 1，电量不足以打电话发短信 2，电量过低 3-6可以正常使用
    var mVoltageGrade: UByte,
    var mGSMinfoIntensity: UByte,//GSM信息-100
    //预留扩展位  终端当前语言 0x00 0x01 中文 0x00 0x02英文
    //902B超长待机设备这个值作为工作模式和设备休眠状态
    //工作模式：
    // 0x00 =正常模式
    // 0x01 =智能休眠
    //0x03  =深度休眠
    //0x04  =定时开关机
    //设备休眠状态
    //0x00=正常
    //0x01=休眠
    var mExt: TerminaExt? = null //920B 才选择这个属性
)

data class TerminalInfo(
    var mDefense: UByte,
    var mAcc: UByte,
    var mAnElectric: UByte,
    var mWarring: UByte,
    var mGpsPostion: UByte,
    var mOilElectricity: UByte
)

/**
 * 终端扩展位
 * <p>
 * 部分终端设置当前语言 0x00 0x01 中文 0x00 0x02英文
 * 部分终端 语言的第一位用作设备外接电压
 * //902B超长待机设备这个值作为工作模式和设备休眠状态
 * //工作模式：
 * // 0x00 =正常模式
 * // 0x01 =智能休眠
 * //0x03  =深度休眠
 * //0x04  =定时开关机
 * //设备休眠状态
 * //0x00=正常
 * //0x01=休眠
 */
data class TerminaExt(
    var mN01: UByte,
    var mNo2: UByte
)
