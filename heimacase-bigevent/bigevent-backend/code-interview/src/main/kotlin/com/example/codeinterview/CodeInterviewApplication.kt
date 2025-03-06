package com.example.codeinterview

import cn.uncleyumo.utils.ColorPrinter
import cn.uncleyumo.utils.LogPrinter
import cn.uncleyumo.utils.enum.BackGroundColorEnum
import cn.uncleyumo.utils.enum.FrontColorEnum
import cn.uncleyumo.utils.enum.QuickTemplateEnum
import cn.uncleyumo.utils.pojo.MetaPrinter
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CodeInterviewApplication

fun main(args: Array<String>) {
    val applicationContext = runApplication<CodeInterviewApplication>(*args)
    val applicationName = applicationContext.environment.getProperty("applicationName")

    MetaPrinter
        .addFontColor(FrontColorEnum.CYAN)
        .addBackgroundColor(BackGroundColorEnum.RED)
        .println("Application Name: ${applicationName ?: "Unknown Application Name"}")

}
