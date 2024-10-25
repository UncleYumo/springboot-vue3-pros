package com.itheima.springbootquickstart.service

import com.itheima.springbootquickstart.pojo.EmailProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService {

    @Autowired
    lateinit var emailProperties: EmailProperties

    fun getEmailPropertiesInfo(): String {
        return "EmailProperties: user=${emailProperties.user}, " +
                "subject=${emailProperties.subject}, " +
                "message=${emailProperties.message}"
    }
}
