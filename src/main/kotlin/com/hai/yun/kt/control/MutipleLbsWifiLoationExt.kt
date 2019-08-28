package com.hai.yun.kt.control

import com.hai.yun.kt.model.Lac_ci_rssi
import com.hai.yun.kt.model.MutipleLbsWifiLoation
import com.hai.yun.kt.utils.toUBytes
import com.hai.yun.kt.utils.toUbyteArray

fun MutipleLbsWifiLoation.getContent(): UByteArray {
    return mutableListOf<UByte>().let {
        //时间
        if (time != null) {
            it.addAll(time!!.toUbyteArray())
        }
        //mcc
        it.addAll(mcc.toUBytes())
        //mnc
        it.add(mnc)
        //lac_ci_rssi
        for (index in 0..6) {
            if (index < lac_ci_rssi.size) {
                val lacCiRssi = lac_ci_rssi[index]
                it.addAll(lacCiRssi.lac.toUBytes())
                it.addAll(lacCiRssi.ci)
                it.add(lacCiRssi.rssi)
            } else {
                val temp = Lac_ci_rssi()
                it.addAll(temp.lac.toUBytes())
                it.addAll(temp.ci)
                it.add(temp.rssi)
            }
        }

        //提前量
        it.add(timeAdvanced)
        //wifi数量
        it.add(wifiNumber)

        wifiInfo.forEach { wf ->
            it.addAll(wf.wifiMac)
            it.add(wf.wifiStrong)
        }
        it.toUByteArray()
    }
}