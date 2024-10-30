package com.itheima

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTDecodeException
import com.uncleyumo.utils.Color_Print_Utils
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.collections.HashMap

/**
 * @author uncle_yumo
 * @fileName JwtTest
 * @createDate 2024/10/29 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

class JwtTest {

//    @Test
    fun testGen() {
        val claims = HashMap<String, Any>().apply {
            put("id", 1)
            put("username", "张三")
        }
        // 生成JWT
        JWT.create()
            .withClaim("user", claims)  // 添加载荷
            .withExpiresAt(Date(System.currentTimeMillis() + 1000 * 10))  // 设置过期时间：10秒
            .sign(Algorithm.HMAC256("itheima"))  // 使用HS256算法进行签名
            .also { println(it) }  // 打印JWT
    }


    @Test
    fun testParse() {
        // 定义JWT，模拟从客户端获取的JWT
        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGFpbXMiOnsiaWQiOiI0IiwidXNlcm5hbWUiOiJqaW1teSJ9LCJleHAiOjE3MzAxNTkxMjF9.yoEtYecpFgj07bX7J1UXizftVngIuJZr6q333wFa30k"

        // 解析JWT
        try {
            JWT.require(Algorithm.HMAC256("itheima"))
                .build()
                .verify(token)
                .claims.also {
                    for ((key, value) in it) {
                        println("$key: $value")
                        if (key == "user") {
                            val userJson = value.asMap()
                            println("id: ${userJson["id"]} | username: ${userJson["username"]}")
                        }
                    }
                }
        } catch (e: Exception) {
            Color_Print_Utils.getInstance().printlnRed("JWT解析错误！！！\n$e")
        }
    }
}