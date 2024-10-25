package com.itheima.controller

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

@RestController
class FirstController {
    @RequestMapping("/hello")
    fun hello(name: String): String = "Hello Spring Boot | Name: $name!"
}