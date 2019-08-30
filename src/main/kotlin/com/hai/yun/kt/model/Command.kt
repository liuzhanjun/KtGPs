package com.hai.yun.kt.model

import com.hai.yun.kt.utils.toAsciiUbytes
import org.joda.time.DateTime

/**
@author liuzhanjun
 **/
enum class Command(val cmd: Cmd) {
    //中断油电
    DYD(Cmd("DYD")),
    //恢复油电
    HFYD(Cmd("HFYD")),
    //查看位置
    DWXX(Cmd("DWXX")),
    //设置和删除中心号码
    CENTER(Cmd("CENTER")),
    //设置运动上传时间
    TIMER(Cmd("TIMER")),
    //设置和关闭震动报警
    VIBRATION(Cmd("VIBRATION")),
    //重启设备
    RESET(Cmd("RESET")),
    //恢复出厂设置
    FACTORY(Cmd("FACTORY")),
    //下发录音指令
    SOUND(Cmd("SOUND")),
    //下发监听指令
    LISTEN(Cmd("LISTEN")),
    //设置工作模式
    SLEEP(Cmd("SLEEP")),
    //定时开关机
    POWER(Cmd("POWER")),
    //定时开关机工作时间
    POFF(Cmd("POFF")),
    //查询设备状态
    STATUS(Cmd("STATUS")),
    //查询设备参数
    PARAM(Cmd("PARAM"))
    ;

}


inline class Cmd(private val cmd: String) {
    fun getCmdHead(): String {
        return cmd
    }

    private fun getCmdContent(vararg value: Int): UByteArray {
        val stringBuffer = StringBuffer("${cmd}")
        value.forEach {
            stringBuffer.append(",${it}")
        }
        stringBuffer.append("#")
        return stringBuffer.toString().toAsciiUbytes()
    }

    //查询设备状态
    fun getSTATUSCmd(): UByteArray {
        return getCmdContent()
    }

    //查询设备参数
    fun getPARAMCmd(): UByteArray {
        return getCmdContent()
    }


    //定时开关机工作时间 time 3-10分钟
    fun getPOFFCmd(time: Int): UByteArray {
        return getCmdContent(time)
    }

    //定时开关机
    fun getPowerCmd(time: Int): UByteArray {
        return getCmdContent(time)
    }

    //设置工作模式 0正常模式，1智能休眠模式 2深度休眠模式
    fun getSleepCmd(mode: Int): UByteArray {
        return getCmdContent(mode)
    }

    //下发监听指令 number 0,1
    fun getLISTENCmd(number: Int): UByteArray {
        return getCmdContent(number)
    }

    //下发录音指令
    fun getSOUNDCmd(): UByteArray {
        return getCmdContent(1)
    }

    //恢复出厂设置
    fun getFACTORCmd(): UByteArray {
        return "${cmd}#".toAsciiUbytes()
    }

    //重启设备
    fun getRESETCmd(): UByteArray {
        return "${cmd}#".toAsciiUbytes()
    }

    //设置震动报警
    // sensitivity灵敏度1-5 vibMethod 报警方式：vibMethod 1:电话，2短信，3电话+短信
    fun getSettingVIBRATIONCmd(sensitivity: Int, vibMethod: Int): UByteArray {
        return getCmdContent(sensitivity, vibMethod)
    }

    //关闭震动报警
    fun getCloseVIBRATIONCmd(): UByteArray {
        return getCmdContent(0)
    }

    //time的值在1-6之间
    fun getSetTimeCmd(time: Int): UByteArray {
        return getCmdContent(time * 10)
    }

    fun getDYDCmd(): UByteArray {
        return "${cmd},000000#".toAsciiUbytes()
    }

    fun getSuccess(): UByteArray {
        return "${cmd}=Success!".toAsciiUbytes()
    }

    fun getDYDFail(speed: Float? = null): UByteArray {
        if (speed != null) {
            return "${cmd}=Speed Limit, Speed ${speed}Km/h".toAsciiUbytes()
        }
        return "${cmd}=Unvalued Fix".toAsciiUbytes()
    }

    fun getHFYDCmd(): UByteArray {
        return "${cmd},000000#".toAsciiUbytes()
    }

    fun getFail(): UByteArray {
        return "${cmd}=Fail!".toAsciiUbytes()
    }

    fun getDWXXCmd(): UByteArray {
        return "${cmd},000000#".toAsciiUbytes()
    }

    // 纬度方向 0：南纬 1：北纬
    // 经度方向 0：东经 1：西经
    fun getDWXXSuccess(
        lat_sym: Int, lat_d: Int, lat_m: Double,
        lon_sym: Int, lon_d: Int, lon_m: Double,
        course: Int, speed: Float, dateTime: DateTime
    ): UByteArray {
        var latSymbol = "S"
        if (lat_sym == 0) {
            latSymbol = "S"
        } else {
            latSymbol = "N"
        }
        var lonSymbol = "E"
        if (lon_sym == 0) {
            lonSymbol = "E"
        } else {
            lonSymbol = "W"
        }
        return ("${cmd}=Lat:${latSymbol}${lat_d}d${String.format("%.4f", lat_m)}m," +
                "Lon:${lonSymbol}${lon_d}d${String.format("%.4f", lon_m)}m," +
                "Course:${course},Speed:${String.format("%.2f", speed)}," +
                "DateTime:${dateTime.toString("yy-MM-dd hh:mm:ss")}").toAsciiUbytes()


    }

    fun getDWXXFail(): UByteArray {
        return "${cmd}=Command Error!".toAsciiUbytes()
    }

    fun getDWXXLoactionFail(): UByteArray {
        return ("${cmd}=Lat:," +
                "Lon:," +
                "Course:," +
                "DateTime:-:").toAsciiUbytes()
    }

    fun getAddCenterCmd(phoneNumber: String): UByteArray {
        return "${cmd},A,${phoneNumber}#".toAsciiUbytes()
    }

    fun getDeleteCenterCmd(): UByteArray {
        return "${cmd},D#".toAsciiUbytes()
    }

    fun getCenterSuccess(): UByteArray {
        return "${cmd}=Success!".toAsciiUbytes()
    }

    fun getCenterFail(): UByteArray {
        return "${cmd}=Fail!".toAsciiUbytes()
    }
}
