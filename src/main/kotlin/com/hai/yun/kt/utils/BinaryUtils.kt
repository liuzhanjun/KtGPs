package com.hai.yun.kt.utils


import com.hai.yun.java.NumberUtils
import java.lang.IndexOutOfBoundsException
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.ArrayList

/**
 *
 * 将UInt转成Ubyte数组
 * @author liuzhanjun
 * @date 2019/8/24 10:18
 * @param [bitLen] 指定长度 1-4
 * @return
 */
fun UInt.toUbytes(bitLen: Int): UByteArray {
    var bitLen_te = bitLen - 1
    if (bitLen > 4 || bitLen < 1) {
        bitLen_te = 3
    }
    val bytes: UByteArray = UByteArray(bitLen_te + 1)
    for (i in 0..bitLen_te) {
        // this>>((i)*8)&0xFF
        bytes[bitLen_te - i] = ((this shr (i * 8)) and 0xFFu).toUByte()
    }
    return bytes
}


/**
 *
 * uInt转UShortArray
 * @author liuzhanjun
 * @date 2019/8/24 10:19
 * @param [bitLen] 指定长度 1-2
 * @return
 */
fun UInt.toUShorts(bitLen: Int): UShortArray {
    var bitLen_te = bitLen - 1
    if (bitLen > 2 || bitLen < 1) {
        bitLen_te = 1
    }
    val bytes: UShortArray = UShortArray(bitLen_te + 1)
    for (i in 0..bitLen_te) {
        // this>>(i*16)&0xffff
        bytes[bitLen_te - i] = ((this shr (i * 16)) and 0xFFFFu).toUShort()
    }
    return bytes
}

/**
 * 将Ushort转成无符号字byte数组
 */
fun UShort.toUBytes(): UByteArray {
    val bytes: UByteArray = UByteArray(2)
    for (i in 0..1) {
//        bytes[2 - i] = ((this shr (i * 8)) and 0xFFu).toUByte()
        bytes[1 - i] = ((this.toUInt() shr (i * 8)) and 0xffu).toUByte()
    }
    return bytes
}

/**
 *
 * UShort 转16进制字符串数组
 * @author liuzhanjun
 * @date 2019/8/24 14:45
 * @param []
 * @return
 */
fun UShort.toOxArray(): MutableList<String> {
    return this.toUBytes().toOxArray()
}

/**
 * 将16进制字符串转成无符号byte数组
 */
fun String.oxToUBytes(): UByteArray {
    val matcher = Pattern.compile("[0-9|a-f|A-F]{1,2}").matcher(this.trim().reversed())
    val lk = LinkedList<String>()
    while (matcher.find()) {
        val str = matcher.group().reversed()
        lk.addFirst(str)
    }
    val ubyteArrayOf = UByteArray(lk.size)
    lk.forEachIndexed() { index, values ->
        ubyteArrayOf.set(index, Integer.parseInt(values, 16).toUByte())
    }
    return ubyteArrayOf
}

/**
 *
 * 将无符号数组转成16进制字符数组
 * @author liuzhanjun
 * @date 2019/8/24 10:20
 * @param []
 * @return
 */
fun UByteArray.toOxArray(): MutableList<String> {
    val result = ArrayList<String>()
    this.forEach {
        result.add(it.toOx())
    }
    return result.toMutableList()
}

/**
 *
 * 将2位UbyteArray 转成uShort
 * @author liuzhanjun
 * @date 2019/8/24 14:42
 * @param []
 * @return
 */
fun UByteArray.toUShort(): UShort {
    if (this.size > 2) {
        throw IndexOutOfBoundsException()
    }
    if (this.size == 2) {
        return ((this[0].toUInt() shl 8) or this[1].toUInt()).toUShort()
    } else {
        return this[0].toUShort()
    }
}


/**
 *
 * 无符号byte转成16进制字符
 * @author liuzhanjun
 * @date 2019/8/24 10:21
 * @param []
 * @return
 */
fun UByte.toOx(): String {
    val sb = StringBuilder(8)
    val b = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')
    sb.append(b[(this.toInt() % 16)])
//    n = (n / 16).toByte().toInt()
    val n = (this.toInt() / 16).toUByte()
    sb.append(b[(n.toInt() and 0xff) % 16])
    return sb.reverse().toString()
}