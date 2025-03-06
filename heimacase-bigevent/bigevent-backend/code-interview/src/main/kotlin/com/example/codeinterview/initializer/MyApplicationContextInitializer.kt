package com.example.codeinterview.initializer

import cn.uncleyumo.utils.ColorPrinter
import cn.uncleyumo.utils.enum.QuickTemplateEnum
import cn.uncleyumo.utils.pojo.MetaPrinter
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.core.env.MapPropertySource

/**
 * @author uncle_yumo
 * @fileName MyApplicationContextInitializer
 * @createDate 2024/11/21 November
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

class MyApplicationContextInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    override fun initialize(applicationContext: ConfigurableApplicationContext) {

        // IOC容器对象创建完毕后，执行自定义初始化逻辑
        // 准备属性
        val myMap = mutableMapOf<String, Any>()
//        myMap.plus("applicationName" to "big-event")
        myMap["applicationName"] = "big-event"

        // 获取一个属性资源管理对象
        val environment = applicationContext.environment
        val propertySources = environment.propertySources  // 获取属性资源管理对象

        // 注册
        propertySources.addLast(MapPropertySource("myMap", myMap))  // addLast() 最后添加属性源，这样可以覆盖已有的属性源

    }

}