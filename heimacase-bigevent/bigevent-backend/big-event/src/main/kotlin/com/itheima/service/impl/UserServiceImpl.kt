package com.itheima.service.impl

import com.itheima.mapper.UserMapper
import com.itheima.pojo.User
import com.itheima.service.UserService
import com.itheima.utils.Md5Util
import com.itheima.utils.ThreadLocalUtil
import com.uncleyumo.utils.Color_Print_Utils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

/**
 * @author uncle_yumo
 * @fileName UserServiceImpl
 * @createDate 2024/10/27 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

@Service  // 标注为 Spring Bean
class UserServiceImpl: UserService {

    @Autowired
    lateinit var userMapper: UserMapper

    override fun findByUserName(username: String): User? {
        return userMapper.findByUserName(username)?.colorPrinterCyanBlackWithUser()
    }

    override fun register(username: String, password: String) {
        // 加密用户名-md5
        val pwd = Md5Util.getMD5String(password)
        Color_Print_Utils.getInstance().printlnCyan("UserServiceImpl.findByUserName() | 加密后的密码：$pwd")
        userMapper.addUser(username, pwd)
    }

    override fun update(user: User) {
        userMapper.update(user.apply {
            updateTime = LocalDateTime.now()
        })
    }

    override fun updateAvatar(avatarUrl: String) {
        val map = ThreadLocalUtil.get() as Map<*, *>
        userMapper.updateAvatar(avatarUrl, Integer.parseInt(map["id"].toString()))
    }

    override fun updatePwd(s: String) {
        val map = ThreadLocalUtil.get() as Map<*, *>
        userMapper.updatePwd(Md5Util.getMD5String(s), (map["id"] as String).toInt())
    }
}