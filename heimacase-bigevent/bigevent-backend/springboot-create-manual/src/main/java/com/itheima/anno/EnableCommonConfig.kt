package com.itheima.anno

import com.uncleyumo.configure.CommonImportSelector
import org.springframework.context.annotation.Import

/**
 * @author uncle_yumo
 * @fileName EnableCommonConfig
 * @createDate 2024/10/26 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

@Target(AnnotationTarget.CLASS)  // 注解作用于类
@Retention(AnnotationRetention.RUNTIME)  // 注解在运行时有效
@Import(CommonImportSelector::class)
annotation class EnableCommonConfig {
}