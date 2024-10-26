package com.itheima.controller

import com.itheima.pojo.User
import com.itheima.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author uncle_yumo
 * @fileName FirstController
 * @createDate 2024/10/24 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

@RestController  // 注解RestController，表示这是一个控制器类，所有方法都返回JSON数据
class FirstController {

    @Autowired
    lateinit var userService: UserService

    @RequestMapping("/hello")
    fun hello(name: String): String = "Hello Spring Boot | Name: $name!"

    @GetMapping("/user/{id}")
    fun findUserById(@PathVariable id: Int): String {
        println("find user by id: $id")
        return userService.findById(id)?.toString()?: "User not found"
    }
}