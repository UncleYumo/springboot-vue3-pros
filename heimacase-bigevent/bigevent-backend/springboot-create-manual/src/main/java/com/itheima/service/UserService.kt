package com.itheima.service

import com.itheima.pojo.User

/**
 * @author uncle_yumo
 * @fileName UserService
 * @createDate 2024/10/25 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

interface UserService {
    fun findById(id: Int): User?
}