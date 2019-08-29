package com.hai.yun.kt.model

import java.io.File

/**
@author liuzhanjun
 **/
data class RecordPkg(
    var fileType: UByte,//文件类型
    var fileLen: UInt,//文件总长度
    var filePkgNum: UByte,//文件总包数
    var fileCurPkgIndex: UByte,//文件当前包序列
    var curContentLen: UShort,//当前包内容长度
    var fileDataContent: UByteArray//分割后的数据内容
)

class SpliteRecord(var file: File, var fileType: UByte) {
    val records: MutableList<RecordPkg> = mutableListOf()
}