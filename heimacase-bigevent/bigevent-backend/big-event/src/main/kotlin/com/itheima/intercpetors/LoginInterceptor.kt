package com.itheima.intercpetors

import com.itheima.pojo.ResultInfo
import com.itheima.utils.JwtUtil
import com.uncleyumo.utils.Color_Print_Utils
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
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
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        // 令牌验证
        val token = request.getHeader("Authorization").removePrefix("Bearer ")
        var claims: Map<String, Any>? = null
        return try {
            claims = JwtUtil.parseToken(token)
            Color_Print_Utils.getInstance().printlnYellow("\n登录拦截器 | token 解析成功:\n$claims")
            true  // 验证通过 放行
        } catch (e: Exception) {
            Color_Print_Utils.getInstance().printlnRed("\n登录拦截器 | token 解析失败:\n$e")
            response.status = 401  // 未授权
            false  // 验证失败 阻止请求
        }
    }
}