package com.hai.yun.kt.control

import com.hai.yun.kt.model.RecordPkg
import com.hai.yun.kt.model.SpliteRecord
import com.hai.yun.kt.utils.printOxString
import com.hai.yun.kt.utils.toUbyteArray
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.FileInputStream
import java.nio.ByteBuffer

fun SpliteRecord.spliteFile(pkgNumber: Int) = runBlocking {
    records.clear()
    launch {
        val fis = FileInputStream(file)
        val size = fis.channel.size()
        println(size)
        var lens = arrayOfNulls<Long>(pkgNumber)
        lens.forEachIndexed { index, l ->
            lens[index] = size / pkgNumber
        }
        lens[pkgNumber - 1] = lens[pkgNumber - 1]?.plus(size % pkgNumber)
        lens.forEach {
            println("长度=" + it)
        }
        val fileChannel = fis.channel
        lens.forEachIndexed { index, len ->
            val bf = ByteBuffer.allocate(len!!.toInt())
            fileChannel.read(bf)
            bf.flip()
            val tempbt = mutableListOf<Byte>()
            while (bf.hasRemaining()) {
                tempbt.add(bf.get())
            }
            records.add(
                RecordPkg(
                    fileType,
                    size.toUInt(),
                    pkgNumber.toUByte(),
                    index.toUByte(),
                    len!!.toUShort(),
                    tempbt.toByteArray().toUByteArray()
                )
            )
            bf.clear()
        }
        fileChannel.close()
        fis.close()
        println("end======================")
    }
    this
}