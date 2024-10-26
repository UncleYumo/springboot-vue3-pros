package com.itheima.springbootautoconfig

import com.uncleyumo.utils.Color_Print_Utils
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringbootAutoConfigApplication

fun main(args: Array<String>) {
    val context = runApplication<SpringbootAutoConfigApplication>(*args)
    val bean = context.getBean("dispatcherServlet")
    Color_Print_Utils.getInstance().printlnCyanBlack(bean.toString())
}
