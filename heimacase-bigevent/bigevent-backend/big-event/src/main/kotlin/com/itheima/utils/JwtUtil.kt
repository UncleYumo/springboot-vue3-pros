package com.itheima.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.Date

/**
 * @author uncle_yumo
 * @fileName JwtUtil
 * @createDate 2024/10/29 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

class JwtUtil {
    companion object {
        private const val KEY = "itheima"

        fun genToken(claims: Map<String, Any>): String {
            return JWT.create()
                .withClaim("claims", claims)  // 负添加载
                .withExpiresAt(Date(System.currentTimeMillis() + 1000 * 60 * 10))  // 设置过期时间：10分钟
                .sign(Algorithm.HMAC256(KEY))
        }

        fun parseToken(token: String): Map<String, Any> {
            return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("claims")  // 解析负载
                .asMap()
        }
    }
}