package com.hai.yun.kt.net

import com.hai.yun.kt.utils.printOxString
import com.hai.yun.kt.utils.println
import org.apache.mina.core.buffer.IoBuffer
import org.apache.mina.core.service.IoHandlerAdapter
import org.apache.mina.core.session.IoSession
import org.slf4j.LoggerFactory

/**
@author liuzhanjun
 **/
class TcpIoAdapter : IoHandlerAdapter() {
    internal var logger = LoggerFactory.getLogger(TcpIoAdapter::class.java)
    internal var count = 1

    override fun messageReceived(session: IoSession?, message: Any?) {
        val iobuffer: IoBuffer = message as IoBuffer
        var bytes = ByteArray(iobuffer.limit())
        iobuffer.get(bytes)
        bytes.toUByteArray().printOxString()
        count++
    }

    override fun sessionClosed(session: IoSession?) {
        "直接断开了".println()

    }

    override fun messageSent(session: IoSession?, message: Any?) {
        super.messageSent(session, message)
    }
}