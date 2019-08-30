package com.hai.yun.kt.net

import org.apache.mina.core.session.IoSession
import org.apache.mina.filter.codec.ProtocolCodecFactory
import org.apache.mina.filter.codec.ProtocolDecoder
import org.apache.mina.filter.codec.ProtocolEncoder

/**
@author liuzhanjun
 **/
class MessageCoderFactory : ProtocolCodecFactory {
    override fun getEncoder(ioSession: IoSession): ProtocolEncoder {
        return MinaMessageEncoder()
    }

    override fun getDecoder(ioSession: IoSession): ProtocolDecoder {
        return MinaMessageDecoder()
    }
}