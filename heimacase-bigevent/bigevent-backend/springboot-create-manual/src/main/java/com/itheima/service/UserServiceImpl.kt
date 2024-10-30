package com.itheima.service

import com.itheima.mapper.UserMapper
import com.itheima.pojo.User
import com.itheima.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author uncle_yumo
 * @fileName UserServiceImpl
 * @createDate 2024/10/25 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

@Service
class UserServiceImpl: UserService {

    @Autowired
    lateinit var userMapper: UserMapper

    override fun findById(id: Int): User? {
        return userMapper.findById(id)
    }

}