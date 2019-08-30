package com.hai.yun.kt.net

import org.apache.mina.core.buffer.IoBuffer
import org.apache.mina.core.session.IoSession
import org.apache.mina.filter.codec.ProtocolCodecFilter
import org.apache.mina.transport.socket.nio.NioSocketConnector
import java.net.InetSocketAddress

enum class MinaTcpClient {
    client {
        override fun init(): MinaTcpClient {
            println("==============")

            conn()
            return this
        }
    };

    private var mSession: IoSession? = null
    abstract fun init(): MinaTcpClient
    val PORT = 18567
    val HOST = "localhost"
    private val conn = NioSocketConnector()
    private val adapter = TcpIoAdapter()
    fun conn(){
        conn.handler = adapter
        conn.filterChain.addLast("message_f", ProtocolCodecFilter(MessageCoderFactory()))
        val connectFuture = conn.connect(InetSocketAddress(HOST, PORT))
        connectFuture.awaitUninterruptibly()
        mSession = connectFuture.getSession()
    }

    fun sendMessage(msg: ByteArray) {
        mSession!!.write(IoBuffer.wrap(msg))
    }

    fun closConn() {
        // 关闭会话，待所有线程处理结束后
        conn.dispose(true)
    }
}