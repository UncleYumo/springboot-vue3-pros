package com.itheima

import com.itheima.pojo.ResultInfo
import com.itheima.utils.CreateImageCode
import com.itheima.utils.Md5Util
import com.uncleyumo.utils.Color_Print_Utils
import jakarta.servlet.http.HttpServletResponse
import java.time.LocalDateTime

/**
 * @author uncle_yumo
 * @fileName BigEventApplicationTest
 * @createDate 2024/10/27 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

class BigEventApplicationTest

fun main() {
    Color_Print_Utils.getInstance().printlnPurple(testMd5Generator("1234567"))
}

fun testMd5Generator(originStr: String): String {
    return Md5Util.getMD5String(originStr)
}

fun testThreadLocal() {
    val now = LocalDateTime.now()
    println(now)
    Color_Print_Utils.getInstance().printlnGreen(now.toString())
    Color_Print_Utils.getInstance().programStart()
}