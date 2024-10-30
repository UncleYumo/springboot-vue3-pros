package com.itheima.pojo

import com.uncleyumo.utils.Color_Print_Utils
import net.minidev.json.annotate.JsonIgnore
import java.time.LocalDateTime

/**
 * @author uncle_yumo
 * @fileName User
 * @createDate 2024/10/26 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

data class User (
    var id: Int? = null,  // 主键id
    var username: String? = null, // 用户名

    var password: String? = null, // 密码
    var nickname: String? = null, // 昵称

//    @JsonIgnore  // 在json序列化时忽略该字段
    var email: String? = null, // 邮箱
    var userPic: String? = null, // 用户头像地址

    var createTime: LocalDateTime? = null, // 创建时间 | Sql对应为 DATETIME
    var updateTime: LocalDateTime? = null // 更新时间 | Sql对应为 DATETIME
): SelfColorPrinter {
    override fun colorPrinterCyanBlack() {
        Color_Print_Utils.getInstance().printlnCyanBlack(this.toString())
    }
    fun colorPrinterCyanBlackWithUser(): User {
        Color_Print_Utils.getInstance().printlnCyanBlack(this.toString())
        return this
    }

}

