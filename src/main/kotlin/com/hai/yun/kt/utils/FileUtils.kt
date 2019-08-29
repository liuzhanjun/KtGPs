package com.hai.yun.kt.utils

import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.runBlocking
import java.io.*


//suspend fun File.ReadUbyteArray(len: Int): UByteArray? {
//    val fileInputStream = FileInputStream(this)
//}