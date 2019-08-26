package com.hai.yun.kt.model

/**
@author liuzhanjun
 **/
data class HeartBeatPkg(
    var mTerminalInfo: TerminalInfo,//终端信息
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
    var mExt: TerminaExt //920B 才选择这个属性
)

data class TerminalInfo(
    var mDefense: UInt,//0：撤防，1：设防
    var mAcc: UInt,//0:ACC低 1：Acc高
    var mAnElectric: UInt,// 0：未接外电 1：已接外电
    var mWarring: UInt,// 0：正常 1：震动报警 2：断电报警 3 ：低电报警 4：SOS报警
    var mGpsPostion: UInt,//0：GPS未定位 1：已定位
    var mOilElectricity: UInt//0：油电接通 1：油电断开
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
 * 还有其他机型
 * 未接电压和语言位
 *
 * 报警信息
 * 第一字节
 * 0x00 正常报警
 * 0x01 SOS报警
 * 0x02 断电报警
 * 0x03 震动报警
 * 0x04 进围栏报警
 * 0x05 出围栏报警
 * 第二字节
 * 0x01中文
 * 0x02英文
 *
 */
data class TerminaExt(
    var mN01: UByte,
    var mNo2: UByte
)
