package com.hai.yun.kt.net

import org.apache.mina.core.session.IoSession
import org.apache.mina.filter.codec.ProtocolEncoderAdapter
import org.apache.mina.filter.codec.ProtocolEncoderOutput

/**
@author liuzhanjun
 **/
class MinaMessageEncoder: ProtocolEncoderAdapter() {
    override fun encode(session: IoSession?, message: Any?, out: ProtocolEncoderOutput?) {

    }
}