package com.itheima.service

import com.itheima.pojo.User
import org.springframework.stereotype.Service

/**
 * @author uncle_yumo
 * @fileName UserService
 * @createDate 2024/10/27 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

interface UserService {
    fun findByUserName(username: String): User?
    fun register(username: String, password: String)
}