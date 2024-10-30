package com.itheima.exception

import com.itheima.pojo.ResultInfo
import com.uncleyumo.utils.Color_Print_Utils
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * @author uncle_yumo
 * @fileName GlobalExceptionHandler
 * @createDate 2024/10/27 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

@RestControllerAdvice  // 全局异常处理类
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)  // 捕获所有异常
    fun handleException(e: Exception): ResultInfo {
        Color_Print_Utils.getInstance().printlnYellow("\n全局异常处理 handleException() | e.message: ${e.message?:"Unknown Exception's message"}")
        return ResultInfo.error(msg = e.message?: "出现未知异常 请联系管理员", data = null)
    }
}