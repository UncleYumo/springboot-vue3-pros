package com.itheima.controller

import com.itheima.pojo.ResultInfo
import com.itheima.pojo.User
import com.itheima.service.UserService
import com.itheima.utils.JwtUtil
import com.itheima.utils.Md5Util
import com.itheima.utils.ThreadLocalUtil
import com.uncleyumo.utils.Color_Print_Utils
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.hibernate.validator.constraints.URL
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.web.bind.annotation.*
import java.util.concurrent.TimeUnit

/**
 * @author uncle_yumo
 * @fileName UserController
 * @createDate 2024/10/27 October
 * @school 无锡学院
 * @studentID 22344131
 * @description 用户相关接口
 */

//@CrossOrigin(origins = ["http://localhost:63342"], allowCredentials = "true")
@RestController
@RequestMapping("/user")  // 全局路径前缀
open class UserController {

    @Autowired
    lateinit var userService: UserService  // 注入UserService

    @Value("\${uncleyumo.privacy-symbol}")
    lateinit var privacySymbol: String  // 隐私符号

    @Autowired
    lateinit var stringRedisTemplate: StringRedisTemplate

    @PostMapping("/register")
    fun register(
        username: String?,
        password: String?
    ): ResultInfo {
        Color_Print_Utils.getInstance()
            .printlnYellow("\n请求路径: /user/register(POST) | username: $username, password: $password")
        // 验证用户名和密码是否合法
        val verifyResult = verifyUsernameAndPassword(username, password)
        if (verifyResult != "ok") {
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
            userService.register(username, password!!)  // 注册用户
            Color_Print_Utils.getInstance().printlnGreen("Info | 用户注册成功")
            ResultInfo.success()  // 注册成功
        }
    }

    @PostMapping("/login")
    fun login(
        username: String?,
        password: String?,
        response: HttpServletResponse): ResultInfo {
        Color_Print_Utils.getInstance()
            .printlnYellow("\n请求路径: /user/login(POST) | username: $username, password: $password")
        // 验证用户名和密码是否合法
        val verifyResult = verifyUsernameAndPassword(username, password)
        if (verifyResult != "ok") {
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
                    maxAge = 60 * 60  // 有效期1小时
                    path = "/"  // 路径为根路径
                })
            }
            val operations = stringRedisTemplate.opsForValue()  // 操作redis
            operations.set(token, token, 60 * 60, TimeUnit.SECONDS)
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

    /**
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
     */

    @GetMapping("/userInfo")
    fun userInfo(): ResultInfo {
        val map = ThreadLocalUtil.get() as Map<*, *>
        Color_Print_Utils.getInstance().printlnYellow("\n请求路径: /user/userInfo(GET) | ThreadLocal: [${map}]")
        return ResultInfo.success((userService.findByUserName(map["username"] as String) as User).apply {
            this.password = privacySymbol  // 隐藏密码
//            userPic = privacySymbol  // 隐藏头像
//            email = privacySymbol  // 隐藏邮箱
        })
    }

    @PostMapping("/update")
    fun update(@RequestBody user: User): ResultInfo {
        Color_Print_Utils.getInstance().printlnYellow(
            "\n请求路径: /user/update(POST) | user:\n" +
                    "${user.colorPrinterCyanBlackWithUser()}"
        )
        userService.update(user.apply {
            if (id == null || nickname.isNullOrBlank() || email.isNullOrBlank()) return ResultInfo.error("参数错误或缺失")
            // nick匹配正则表达式: 1 - 10位，只能包含字母、数字、下划线
            if (nickname?.matches(Regex("^[a-zA-Z0-9_]{1,10}$")) == false) {
                return ResultInfo.error("昵称格式错误：只能包含字母、数字、下划线，且长度在1-10之间")
            }
            if (email?.matches(Regex("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) == false) {
                return ResultInfo.error("邮箱格式错误")
            }
        })
        // 删除redis中对应的token
        return ResultInfo.success()
    }

    @PatchMapping("/updateAvatar")
    fun updateAvatar(@RequestParam avatarUrl: String): ResultInfo {
        avatarUrl.let {
            Color_Print_Utils.getInstance()
                .printlnYellow("\n请求路径: /user/updateAvatar(PATCH) | avatarUrl: $avatarUrl")
            if (!it.matches(Regex("^https?://.*$"))) return ResultInfo.error("头像URL格式错误")
        }
        userService.updateAvatar(avatarUrl)
        return ResultInfo.success()
    }

    @PatchMapping("/updatePwd")
    fun updatePwd(@RequestBody params: Map<String, String>, @RequestHeader("Authorization") token: String): ResultInfo {
        Color_Print_Utils.getInstance().printlnYellow("\n请求路径: /user/updatePwd(PATCH) | params: $params")

        // 校验参数
        params.also {
            if (it["old_pwd"].isNullOrBlank() || it["new_pwd"].isNullOrBlank() || it["re_pwd"].isNullOrBlank()) {
                return ResultInfo.error("参数错误或缺失")
            }
            val username = ThreadLocalUtil.get() as Map<*, *>
            val user = userService.findByUserName(username["username"] as String)
                ?: return ResultInfo.error("对应username未查询到用户")
            if (user.password != Md5Util.getMD5String(it["old_pwd"] as String)) return ResultInfo.error("输入的旧密码错误")
            if (it["new_pwd"] != it["re_pwd"]) return ResultInfo.error("两次输入的新密码不一致")
            if (it["new_pwd"]?.matches(Regex("^[a-zA-Z0-9_]{5,16}$")) == false) {
                return ResultInfo.error("密码格式错误：只能包含字母、数字、下划线，且长度在5-16之间")
            }
            userService.updatePwd(it["new_pwd"] as String)

            // 删除redis中对应的token
            val operations = stringRedisTemplate.opsForValue()
            operations.operations.delete(token)
        }
        return ResultInfo.success()
    }

    @GetMapping("/test")
    @CrossOrigin(
        origins = [
            "*"
        ], allowCredentials = "false"
    )
    fun testGet(): ResultInfo {
        Color_Print_Utils.getInstance().printlnYellow("\n请求路径: /user/test(GET)")
        return ResultInfo.success("/user/test(GET) | success")
    }

    @PostMapping("/test")
    @CrossOrigin(
        origins = [
            "*"
        ], allowCredentials = "false"
    )
    fun testPost(): ResultInfo {
        Color_Print_Utils.getInstance().printlnYellow("\n请求路径: /user/test(POST)")
        return ResultInfo.success("/user/test(POST) | success")
    }
}