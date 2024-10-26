package com.itheima

import com.itheima.anno.EnableCommonConfig
import com.uncleyumo.configure.CenterConfig
import com.uncleyumo.configure.CommonImportSelector
import com.uncleyumo.utils.Color_Print_Utils
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import

/**
 * @author uncle_yumo
 * @fileName SpringBootCreateManualApplication
 * @createDate 2024/10/24 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

//@Import(value = [CenterConfig::class])
//@ComponentScan(basePackages = ["com.uncleyumo"])
//@Import(CommonImportSelector::class)
@EnableCommonConfig
@SpringBootApplication  // 标注该类是一个SpringBoot启动类
class SpringBootCreateManualApplication {

    // 注入第三方Bean
//    @Bean
//    fun colorPrinter(): Color_Print_Utils {
//        return Color_Print_Utils()
//    }

}

fun main(args: Array<String>) {
    val appContext = runApplication<SpringBootCreateManualApplication>(*args)
    // 调用第三方Bean
    val bean = appContext.getBean(Color_Print_Utils::class.java)
//    var clazz = appContext.getBean("testbean").javaClass
    println(bean)
}
