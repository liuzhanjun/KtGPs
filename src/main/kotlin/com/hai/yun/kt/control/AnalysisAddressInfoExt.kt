package com.hai.yun.kt.control

import com.hai.yun.kt.model.AnalysisAddressInfo
import com.hai.yun.kt.utils.*

fun AnalysisAddressInfo.analysis(data: UByteArray, isEnglish: Boolean): AnalysisAddressInfo {
//    var (mPstartBit, mLength, mAgreement,
//        mCommandLen, mServiceFlag, ADDRESS,
//        separator, address_content, phone_number,
//        endFlag, list_no, check_bit,
//        stop_bit) = this

    var index = 0
    mPstartBit = ubyteArrayOf(data[0], data[1]).toUShort()
    if (isEnglish) {
        mLength = ubyteArrayOf(data[2], data[3])
        index = 4
    } else {
        mLength = ubyteArrayOf(data[2])
        index = 3
    }
    //协议号
    mAgreement = data[index++]
    //指令长度
    if (isEnglish) {
        mCommandLen = ubyteArrayOf(data[index++], data[index++])
    } else {
        mCommandLen = ubyteArrayOf(data[index++])
    }
    //服务器标志
    mServiceFlag = data.sliceArray(index..(index + 3)).toUInt()
    index += 4
    //ADDRESS
    ADDRESS = data.sliceArray(index..(index + 6))
    index += 7
    separator = data.sliceArray(index..(index + 1)).toUShort()
    index += 2

//    //地址内容

    val unicodeToString = data.sliceArray(index..data.size - 1).unicodeToString()
    address_content = unicodeToString.slice(0..(unicodeToString.indexOf("&&") - 1)).string2Unicode().oxToUBytes()
    index += address_content.size + 2

    //电话号码
    phone_number = data.sliceArray(index..(index + 20))
    index += 21
    //结束符
    endFlag = data.sliceArray(index..(index + 1)).toUShort()
    index += 2
    //序列号
    list_no = data.sliceArray(index..(index + 1)).toUShort()
    index += 2
    //校验位
    check_bit = data.sliceArray(index..(index + 1)).toUShort()
    index += 2
    //停止位
    stop_bit = data.sliceArray(index..(index + 1)).toUShort()
    return this
}

fun AnalysisAddressInfo.checked(): Boolean {
    val let = mutableListOf<UByte>().let {
        //添加长度
        it.addAll(mLength)
        //添加协议号
        it.add(mAgreement)
        //指令长度
        it.addAll(mCommandLen)
        //服务器标志
        it.addAll(mServiceFlag.toUbytes(4))
        //address
        it.addAll(ADDRESS)
        //分隔符
        it.addAll(separator.toUBytes())
        //地址内容
        it.addAll(address_content)
        //分隔符
        it.addAll(separator.toUBytes())
        //电话
        it.addAll(phone_number)
        //结束符
        it.addAll(endFlag.toUBytes())
        //序列号
        it.addAll(list_no.toUBytes())
        CRC16.getCRC16(it.toUByteArray())
    }

    if (check_bit xor let == 0.toUShort()) {
        return true
    } else {
        return false
    }
}