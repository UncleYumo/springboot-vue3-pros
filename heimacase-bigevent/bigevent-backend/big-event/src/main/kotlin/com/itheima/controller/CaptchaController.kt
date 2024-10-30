package com.itheima.controller

import com.itheima.pojo.ResultInfo
import com.itheima.utils.CreateImageCode
import com.uncleyumo.utils.Color_Print_Utils
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.http.HttpSession
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.Mapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.text.SimpleDateFormat

/**
 * @author uncle_yumo
 * @fileName CaptchaController
 * @createDate 2024/10/27 October
 * @school 无锡学院
 * @studentID 22344131
 * @description 验证码控制器
 */

@RestController
@CrossOrigin(origins = ["http://localhost:63342"], allowCredentials = "true")
@RequestMapping("/captcha")
class CaptchaController {
    @RequestMapping("/getOutPutStream")
    fun getCaptcha(response: HttpServletResponse, session: HttpSession,timestamp: Long? = null ) {
        // 获取时间戳
        if (timestamp == null) {
            return
        }
        val sendTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp)
        Color_Print_Utils.getInstance().printlnYellow("验证码请求时间: $sendTime")

        // TODO: 验证码生成逻辑
        val createImageCode = CreateImageCode()
        val code = createImageCode.getCode()
        Color_Print_Utils.getInstance().printlnYellow("验证码code: $code")
        createImageCode.write(
            response.apply {
                setHeader("Cache-Control", "no-cache")  // 禁止缓存
                contentType = "image/jpeg"  // 设置响应头
                addCookie(
                    Cookie("captchaCode", code).apply {
                        path = "/"  // 设置cookie作用路径: 整个项目
                        maxAge = 30  // 设置cookie有效期 s
//                        isHttpOnly = true  // 设置cookie是否只在http请求中有效
//                        domain = "localhost"  // 设置cookie作用域: 本地
                    }
                )
            }.outputStream)
        // TODO: 验证码存入=cooke中
//        session.setAttribute("captchaCode", code)
//        Color_Print_Utils.getInstance().printlnYellow("验证码已存入session | sessionID: ${session.id}")
        Color_Print_Utils.getInstance().printlnYellow("验证码已存入cookie | SessionID: ${session.id}")
    }

    @GetMapping("/getBase64Bytes")
    fun getBase64Bytes(timestamp: Long? = null): ResultInfo {
        if (timestamp == null) return ResultInfo.error("时间戳不能为空")
        Color_Print_Utils.getInstance()
            .printlnYellow("获取验证码base64请求时间: ${SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp)}")

        val createImageCode = CreateImageCode()
        val imageAsBase64 = createImageCode.getImageAsBase64()
        val code = createImageCode.getCode()
        Color_Print_Utils.getInstance().printlnYellow("验证码code: $code")
        val imageBytesPlusCode = imageAsBase64
            .plus("?")
            .plus(code)
        return ResultInfo.success(imageBytesPlusCode)
    }
}