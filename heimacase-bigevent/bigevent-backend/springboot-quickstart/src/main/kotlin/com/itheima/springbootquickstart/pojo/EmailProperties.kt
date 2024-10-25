package com.itheima.springbootquickstart.pojo

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * @author uncle_yumo
 * @fileName EmailProperties
 * @createDate 2024/10/25 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

@Component
@ConfigurationProperties(prefix = "email")
class EmailProperties {

//    @Value("\${email.user}")
    lateinit var user: String
//
//    @Value("\${email.code}")
    lateinit var code: String
//
//    @Value("\${email.host}")
    lateinit var host: String
//
//    @Value("\${email.auth}")
    lateinit var auth: String
//
//    @Value("\${email.port}")
    lateinit var port: String
//
//    @Value("\${email.protocol}")
    lateinit var protocol: String
//
//    @Value("\${email.message}")
    lateinit var message: String
//
//    @Value("\${email.subject}")
    lateinit var subject: String

}