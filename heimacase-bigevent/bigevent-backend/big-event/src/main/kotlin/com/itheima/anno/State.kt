package com.itheima.anno

import jakarta.validation.Constraint

/**
 * @author uncle_yumo
 * @fileName State
 * @createDate 2024/11/1 November
 * @school 无锡学院
 * @studentID 22344131
 * @description This is the description of State.
 */

@MustBeDocumented  // 元注解，说明该注解可以被javadoc工具提取文档
@Retention(AnnotationRetention.RUNTIME)  // 元注解，说明该注解的生命周期，在运行时期
@Target(AnnotationTarget.FIELD)  // 元注解，说明该注解可以用于字段
@Constraint(validatedBy = [])  // 元注解，说明该注解可以用于验证
annotation class State(

    // 指定校验失败后的提示信息
    val message: String = "state参数只能是以下值: 已发布、草稿"

    // 指定分组，用于分组校验


)

