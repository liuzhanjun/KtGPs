package com.hai.yun.bean


object AgreeMentNos {
    //登录包
    val loginNO: UByte = 0x01u
    //GPS信息
    val gpsInfo: UByte = 0x10u
    //LBS信息
    val LBSInfo: UByte = 0x11u
    //GPS LBS合并信息
    val GPSAndLBSInfo: UByte = 0x12u
    //状态信息（心跳包）
    val heartbeat: UByte = 0x13u
    //卫星信噪比信息
    val SSNIRInfo: UByte = 0x14u
    //字符串信息
    val StringInfo: UByte = 0x15u
    //报警信息
    val WarningInfo: UByte = 0x16u
    //查询地址信息LBS
    val queryAddressLBS: UByte = 0x17u
    //查询地址信息GPS
    val queryAddressGPS: UByte = 0x1Au
    //LBS多基站定位信息
    val LBSMultipleBaseStations: UByte = 0x18u
    //LBS多基站+wifi定位信息
    val LBSWIFIMultipBaseStations: UByte = 0x2Cu
    //IMSI号上报平台信息 //
    val IMSISendInfo: UByte = 0x90u
    //ICCID号上报平台信息0x94
    val ICCIDSendInfo: UByte = 0x94u
    //录音文件上报平台信息0x8D
    val recordFileSend: UByte = 0x8du
    //服务器向终端发送信息0x80
    val serviceSendToClient: UByte = 0x80u
    //终端响应服务器发送的指令
    var clientSendToService: UByte = 0x15u
}
