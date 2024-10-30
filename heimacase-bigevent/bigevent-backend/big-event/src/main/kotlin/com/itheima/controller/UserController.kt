package com.itheima.controller

import com.itheima.pojo.ResultInfo
import com.itheima.pojo.ResultKT
import com.itheima.pojo.User
import com.itheima.service.UserService
import com.itheima.utils.JwtUtil
import com.itheima.utils.Md5Util
import com.uncleyumo.utils.Color_Print_Utils
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.intellij.lang.annotations.Pattern
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author uncle_yumo
 * @fileName UserController
 * @createDate 2024/10/27 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

@CrossOrigin(origins = ["http://localhost:63342"], allowCredentials = "true")
@RestController
@RequestMapping("/user")  // 全局路径前缀
open class UserController {

    @Autowired
    lateinit var userService: UserService  // 注入UserService

    @Value("\${uncleyumo.privacy-symbol}")
    lateinit var privacySymbol: String  // 隐私符号

    @PostMapping("/register")
    fun register(
        username: String?,
        password: String?
    ): ResultInfo {
        Color_Print_Utils.getInstance().printlnYellow("\n请求路径: /user/register(POST) | username: $username, password: $password")
        // 验证用户名和密码是否合法
        val verifyResult = verifyUsernameAndPassword(username, password)
        if (verifyResult!= "ok") {
            return when (verifyResult) {
                "用户名或密码不能为空" -> ResultInfo.error("用户名或密码不能为空")
                "用户名或密码长度不合法" -> ResultInfo.error("用户名或密码长度不合法")
                else -> ResultInfo.error("未知错误")
            }
        }

        // 根据用户名查询用户是否存在
        val user = userService.findByUserName(username!!)

        return if (user != null) {  // 用户已存在
            Color_Print_Utils.getInstance().printlnYellow("Info | 用户名已被占用")
            ResultInfo.error("用户名已被占用")
        } else {  // 用户不存在
            userService.register(username, password!!)
            Color_Print_Utils.getInstance().printlnGreen("Info | 用户注册成功")
            ResultInfo.success()
        }
    }

    @PostMapping("/login")
    fun login(username: String?, password: String?, response: HttpServletResponse): ResultInfo {
        Color_Print_Utils.getInstance().printlnYellow("\n请求路径: /user/login(POST) | username: $username, password: $password")
        // 验证用户名和密码是否合法
        val verifyResult = verifyUsernameAndPassword(username, password)
        if (verifyResult!= "ok") {
            return when (verifyResult) {
                "用户名或密码不能为空" -> ResultInfo.error("用户名或密码不能为空")
                "用户名或密码长度不合法" -> ResultInfo.error("用户名或密码长度不合法")
                else -> ResultInfo.error("未知错误")
            }
        }
        val loginUser = userService.findByUserName(username!!) ?: return ResultInfo.error("用户名错误")

        return if (loginUser.password != Md5Util.getMD5String(password!!)) {
            Color_Print_Utils.getInstance().printlnYellow("Info | 密码错误")
            ResultInfo.error("密码错误")
        } else {
            Color_Print_Utils.getInstance().printlnGreen("Info | 登录成功")
            // 生成JWT
            val map = HashMap<String, String>().apply {
                put("id", loginUser.id.toString())
                put("username", username)
            }
            val token = JwtUtil.genToken(map)
            response.apply {
                addCookie(Cookie("big-event-token", token).apply {
                    maxAge = 60 * 10  // 10分钟有效期
                    path = "/"  // 路径为根路径
                })
            }
            ResultInfo.success(data = token)
        }
    }

    fun verifyUsernameAndPassword(username: String?, password: String?): String {
        if (username.isNullOrBlank() || password.isNullOrBlank()) {
            Color_Print_Utils.getInstance().printlnYellow("Info | 用户名或密码不能为空")
            return "用户名或密码不能为空"
        }

        if (username.length < 5 || username.length > 16 || password.length < 5 || password.length > 16) {
            Color_Print_Utils.getInstance().printlnYellow("Info | 用户名或密码长度不合法")
            return "用户名或密码长度不合法"
        }
        return "ok"
    }

    @GetMapping("/userInfo")
    fun userInfo(@RequestHeader("Authorization") token: String) : ResultInfo {
        Color_Print_Utils.getInstance().printlnYellow("\n请求路径: /user/userInfo(GET) | token: [${token}]")
        val map = JwtUtil.parseToken(token)
        val username = map["username"] as String
        return ResultInfo.success(userService.findByUserName(username)?.apply {
            this.password = privacySymbol  // 隐藏密码
            userPic = privacySymbol  // 隐藏头像
            email = privacySymbol  // 隐藏邮箱
        })
    }
}