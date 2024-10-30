package com.itheima.config

import com.itheima.intercpetors.LoginInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * @author uncle_yumo
 * @fileName WebConfig
 * @createDate 2024/10/30 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

@Configuration  // 声明当前类是一个配置类
open class WebConfig : WebMvcConfigurer {
    @Autowired
    lateinit var interceptor: LoginInterceptor
    override fun addInterceptors(registry: InterceptorRegistry) {
        // 登录接口和注册接口不拦截
        registry.addInterceptor(interceptor)
            .excludePathPatterns("/user/login", "/user/register")
    }
}