package com.hai.yun.kt.model

import java.util.*

/**
@author liuzhanjun
 **/
data class IccIdPkg(
    var msgType: UByte//信息内型

) {
    var dataContent: LinkedList<UByte>? = null//数据内容

    init {
        println("init")
        if (dataContent == null) {
            dataContent = LinkedList()
        }
    }
}