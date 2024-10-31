package com.itheima.pojo

import com.fasterxml.jackson.annotation.JsonProperty
import com.uncleyumo.utils.Color_Print_Utils
import kotlin.Result

/**
 * @author uncle_yumo
 * @fileName ResultInfo.kt
 * @createDate 2024/10/27 October
 * @school 无锡学院
 * @studentID 22344131
 * @description
 */

class ResultInfo (
    var code: Int,
    var message: String,
    var data: Any?
): SelfColorPrinter {
    companion object {

        fun success(data: Any?): ResultInfo = ResultInfo(0, "预设消息: 操作成功", data ?: "Data Parameter is Null")


        fun success(): ResultInfo = ResultInfo(0, "预设消息: 操作成功", null)


        fun error(msg: String, data: Any? = null): ResultInfo = ResultInfo(1, "预设错误消息: $msg", data)
    }

    override fun colorPrinterCyanBlack() {
        Color_Print_Utils.getInstance().printlnCyanBlack(this.toString())
    }

    override fun toString(): String = "Result(code=$code, message='$message', data=$data)"

}