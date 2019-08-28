package com.hai.yun.kt.utils

class CRC16 {
    companion object {
        val crctab: UShortArray = ushortArrayOf(
            0X00_00u, 0X11_89u, 0X23_12u, 0X32_9Bu, 0X46_24u, 0X57_ADu, 0X65_36u, 0X74_BFu,
            0X8C_48u, 0X9D_C1u, 0XAF_5Au, 0XBE_D3u, 0XCA_6Cu, 0XDB_E5u, 0XE9_7Eu, 0XF8_F7u,
            0X10_81u, 0X01_08u, 0X33_93u, 0X22_1Au, 0X56_A5u, 0X47_2Cu, 0X75_B7u, 0X64_3Eu,
            0X9C_C9u, 0X8D_40u, 0XBF_DBu, 0XAE_52u, 0XDA_EDu, 0XCB_64u, 0XF9_FFu, 0XE8_76u,
            0X21_02u, 0X30_8Bu, 0X02_10u, 0X13_99u, 0X67_26u, 0X76_AFu, 0X44_34u, 0X55_BDu,
            0XAD_4Au, 0XBC_C3u, 0X8E_58u, 0X9F_D1u, 0XEB_6Eu, 0XFA_E7u, 0XC8_7Cu, 0XD9_F5u,
            0X31_83u, 0X20_0Au, 0X12_91u, 0X03_18u, 0X77_A7u, 0X66_2Eu, 0X54_B5u, 0X45_3Cu,
            0XBD_CBu, 0XAC_42u, 0X9E_D9u, 0X8F_50u, 0XFB_EFu, 0XEA_66u, 0XD8_FDu, 0XC9_74u,
            0X42_04u, 0X53_8Du, 0X61_16u, 0X70_9Fu, 0X04_20u, 0X15_A9u, 0X27_32u, 0X36_BBu,
            0XCE_4Cu, 0XDF_C5u, 0XED_5Eu, 0XFC_D7u, 0X88_68u, 0X99_E1u, 0XAB_7Au, 0XBA_F3u,
            0X52_85u, 0X43_0Cu, 0X71_97u, 0X60_1Eu, 0X14_A1u, 0X05_28u, 0X37_B3u, 0X26_3Au,
            0XDE_CDu, 0XCF_44u, 0XFD_DFu, 0XEC_56u, 0X98_E9u, 0X89_60u, 0XBB_FBu, 0XAA_72u,
            0X63_06u, 0X72_8Fu, 0X40_14u, 0X51_9Du, 0X25_22u, 0X34_ABu, 0X06_30u, 0X17_B9u,
            0XEF_4Eu, 0XFE_C7u, 0XCC_5Cu, 0XDD_D5u, 0XA9_6Au, 0XB8_E3u, 0X8A_78u, 0X9B_F1u,
            0X73_87u, 0X62_0Eu, 0X50_95u, 0X41_1Cu, 0X35_A3u, 0X24_2Au, 0X16_B1u, 0X07_38u,
            0XFF_CFu, 0XEE_46u, 0XDC_DDu, 0XCD_54u, 0XB9_EBu, 0XA8_62u, 0X9A_F9u, 0X8B_70u,
            0X84_08u, 0X95_81u, 0XA7_1Au, 0XB6_93u, 0XC2_2Cu, 0XD3_A5u, 0XE1_3Eu, 0XF0_B7u,
            0X08_40u, 0X19_C9u, 0X2B_52u, 0X3A_DBu, 0X4E_64u, 0X5F_EDu, 0X6D_76u, 0X7C_FFu,
            0X94_89u, 0X85_00u, 0XB7_9Bu, 0XA6_12u, 0XD2_ADu, 0XC3_24u, 0XF1_BFu, 0XE0_36u,
            0X18_C1u, 0X09_48u, 0X3B_D3u, 0X2A_5Au, 0X5E_E5u, 0X4F_6Cu, 0X7D_F7u, 0X6C_7Eu,
            0XA5_0Au, 0XB4_83u, 0X86_18u, 0X97_91u, 0XE3_2Eu, 0XF2_A7u, 0XC0_3Cu, 0XD1_B5u,
            0X29_42u, 0X38_CBu, 0X0A_50u, 0X1B_D9u, 0X6F_66u, 0X7E_EFu, 0X4C_74u, 0X5D_FDu,
            0XB5_8Bu, 0XA4_02u, 0X96_99u, 0X87_10u, 0XF3_AFu, 0XE2_26u, 0XD0_BDu, 0XC1_34u,
            0X39_C3u, 0X28_4Au, 0X1A_D1u, 0X0B_58u, 0X7F_E7u, 0X6E_6Eu, 0X5C_F5u, 0X4D_7Cu,
            0XC6_0Cu, 0XD7_85u, 0XE5_1Eu, 0XF4_97u, 0X80_28u, 0X91_A1u, 0XA3_3Au, 0XB2_B3u,
            0X4A_44u, 0X5B_CDu, 0X69_56u, 0X78_DFu, 0X0C_60u, 0X1D_E9u, 0X2F_72u, 0X3E_FBu,
            0XD6_8Du, 0XC7_04u, 0XF5_9Fu, 0XE4_16u, 0X90_A9u, 0X81_20u, 0XB3_BBu, 0XA2_32u,
            0X5A_C5u, 0X4B_4Cu, 0X79_D7u, 0X68_5Eu, 0X1C_E1u, 0X0D_68u, 0X3F_F3u, 0X2E_7Au,
            0XE7_0Eu, 0XF6_87u, 0XC4_1Cu, 0XD5_95u, 0XA1_2Au, 0XB0_A3u, 0X82_38u, 0X93_B1u,
            0X6B_46u, 0X7A_CFu, 0X48_54u, 0X59_DDu, 0X2D_62u, 0X3C_EBu, 0X0E_70u, 0X1F_F9u,
            0XF7_8Fu, 0XE6_06u, 0XD4_9Du, 0XC5_14u, 0XB1_ABu, 0XA0_22u, 0X92_B9u, 0X83_30u,
            0X7B_C7u, 0X6A_4Eu, 0X58_D5u, 0X49_5Cu, 0X3D_E3u, 0X2C_6Au, 0X1E_F1u, 0X0F_78u
        )

        fun getCRC16(bytes: UByteArray): UShort {
            var fcs: UShort = 0xFF_FFu
            for (i in bytes.indices) {
                //fcs = (fcs >> 8) ^ crctab[(fcs ^ bytes[i]) & 0xff];
                fcs =
                    ((fcs.toUInt() shr 8) xor crctab[((fcs.toUInt() xor bytes[i].toUInt()) and 0xffu).toInt()].toUInt()).toUShort()
            }
            return fcs.toUInt().inv().toUShort()
        }

    }
}