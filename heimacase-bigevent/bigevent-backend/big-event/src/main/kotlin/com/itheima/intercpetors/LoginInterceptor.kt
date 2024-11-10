package com.itheima.intercpetors

import com.itheima.utils.JwtUtil
import com.itheima.utils.ThreadLocalUtil
import com.uncleyumo.utils.Color_Print_Utils
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

/**
 * @author uncle_yumo
 * @fileName LoginInterceptor
 * @createDate 2024/10/30 October
 * @school 无锡学院
 * @studentID 22344131
 * @description 登录拦截器
 */

@Component
class LoginInterceptor : HandlerInterceptor {

    @Autowired
    lateinit var stringRedisTemplate: StringRedisTemplate

    // 调用 HandlerInterceptor 接口的 preHandle 方法，在请求处理之前进行拦截
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        // 跨域请求
        if (request.method == "OPTIONS") {
            response.status = 200  // 允许跨域请求
            response.setHeader(
                "Access-Control-Allow-Origin",
                "*"
            )
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT")
            response.setHeader("Access-Control-Max-Age", "3600")
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, content-type")
            Color_Print_Utils.getInstance().printlnGreen("\n登录拦截器 | 跨域预检请求")
            return false  // 验证成功
        }


        // 令牌验证
        val token = request.getHeader("Authorization")

        if (token == null) {
            Color_Print_Utils.getInstance().printlnRed("\n登录拦截器 | token 为空")
            response.status = 401  // 未授权
            return false  // 验证失败 阻止请求
        }

        // 从redis中获取相同的令牌
        val redisToken : String? = stringRedisTemplate.opsForValue().get(token)
        if (redisToken == null) {
            Color_Print_Utils.getInstance().printlnRed("\n登录拦截器 | redis 中不存在相同的令牌")
            response.status = 401  // 未授权
            return false  // 验证失败 阻止请求
        }

        val claims: Map<String, Any>?
        return try {
            claims = JwtUtil.parseToken(token.removePrefix("Bearer "))
            Color_Print_Utils.getInstance().printlnYellow("\n登录拦截器 | token 解析成功:\n$claims")
            ThreadLocalUtil.set(claims)  // 将用户信息存入线程变量中
            true  // 验证通过 放行
        } catch (e: Exception) {
            Color_Print_Utils.getInstance().printlnRed("\n登录拦截器 | token 解析失败:\n$e")
            response.status = 401  // 未授权
            false  // 验证失败 阻止请求
        }
    }


    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: java.lang.Exception?
    ) {
        ThreadLocalUtil.remove()  // 移除线程变量中的用户信息
    }
}