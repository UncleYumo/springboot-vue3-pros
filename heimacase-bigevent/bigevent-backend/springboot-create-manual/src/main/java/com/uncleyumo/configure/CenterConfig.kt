package com.uncleyumo.configure

import com.uncleyumo.utils.Color_Print_Utils
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author uncle_yumo
 * @fileName CenterConfig
 * @createDate 2024/10/26 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

@Configuration
class CenterConfig {
    @Bean
    fun colorPrinter(): Color_Print_Utils {
        return Color_Print_Utils()
    }
}