package com.itheima.springbootquickstart.controller

import com.itheima.springbootquickstart.pojo.EmailProperties
import com.itheima.springbootquickstart.service.EmailService
import org.springframework.beans.factory.annotation.Autowired
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

    @Autowired
    private lateinit var emailService: EmailService

    @RequestMapping("/hello")
    fun hello(): String {
        return emailService.getEmailPropertiesInfo()
    }
}