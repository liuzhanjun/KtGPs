package com.hai.yun.kt.net

import org.apache.mina.core.buffer.IoBuffer
import org.apache.mina.core.session.IoSession
import org.apache.mina.filter.codec.CumulativeProtocolDecoder
import org.apache.mina.filter.codec.ProtocolDecoderOutput
import java.util.ArrayList
import kotlin.experimental.xor

/**
@author liuzhanjun
 **/
class MinaMessageDecoder : CumulativeProtocolDecoder() {
    override fun doDecode(session: IoSession?, ioBuffer: IoBuffer?, out: ProtocolDecoderOutput?): Boolean {

        //粘包问题
//        ioBuffer.l
        val listbyte = ArrayList<Byte>()
        while (ioBuffer!!.position() < ioBuffer.limit()) {
            val temp = ioBuffer.get()
            if (temp.toUInt() xor 0x0D.toUInt() == 0.toUInt()) {
                val end = ioBuffer.get()
                if (end.toUInt() xor 0x0A.toUInt() == 0.toUInt()) {
                    listbyte.add(temp)
                    listbyte.add(end)
                    //读完一条完整的包
                    val pkgs = ByteArray(listbyte.size)
                    for (i in listbyte.indices) {
                        pkgs[i] = listbyte[i]
                    }
                    val buffer = IoBuffer.wrap(pkgs)
                    out!!.write(buffer)
                    return true
                }
            } else {
                listbyte.add(temp)
            }

            //            System.out.println("postion：" + ioBuffer.position() + "   limit:" + ioBuffer.limit() + "  capacity:" + ioBuffer.capacity());

        }
        //分包问题
        val bytes = ByteArray(ioBuffer.limit())
        ioBuffer.rewind()
        ioBuffer.get(bytes)
        if (bytes[ioBuffer.limit() - 1].toUInt() xor 0x0A.toUInt() != 0.toUInt() || bytes[ioBuffer.limit() - 2].toUInt() xor 0x0D.toUInt() != 0.toUInt()) {
            ioBuffer.rewind()
            return false
        }
//        IoBuffer buffer = IoBuffer.wrap(bytes);
//        protocolDecoderOutput.write(buffer);
        return false


    }
}